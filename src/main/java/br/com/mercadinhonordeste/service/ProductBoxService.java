package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Product;
import br.com.mercadinhonordeste.entity.ProductBox;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.repository.ProductBoxRepository;
import br.com.mercadinhonordeste.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBoxService {
    private final ProductBoxRepository repository;
    private final ProductRepository productRepository;

    public ApiResponse<ProductBox> saveBox(ProductBox productBox) {
        ApiResponse<ProductBox> response = new ApiResponse<>();
        ProductBox boxSaved = repository.save(productBox);
        Product product = productRepository.findById(boxSaved.getProduct().getId()).get();
        product.setBox(boxSaved);
        productRepository.save(product);
        return response.of(HttpStatus.CREATED, "Caixa de produtos cadastrada com sucesso", boxSaved);
    }

    public ApiResponse<ProductBox> updateBox(ProductBox productBox) {
        ApiResponse<ProductBox> response = new ApiResponse<>();
        if (!repository.existsById(productBox.getId()))
            return response.of(HttpStatus.NOT_FOUND, "Caixa de produtos não encontrada com o id inforado");
        return response.of(HttpStatus.OK, "Caixa de produtos atualizada com sucesso", repository.save(productBox));
    }

    public ApiResponse<ProductBox> getBoxByCode(String code) {
        ApiResponse<ProductBox> response = new ApiResponse<>();
        Optional<ProductBox> productBox = repository.findByCode(code);
        if (productBox.isEmpty())
            return response.of(HttpStatus.NOT_FOUND, "Nenhuma caixa de produtos encontrada com o código informado");
        return response.of(HttpStatus.OK, "Caixa de produtos encontrada", productBox.get());
    }

    public ApiResponse<ProductBox> getBoxByProductId(Integer productId) {
        ApiResponse<ProductBox> response = new ApiResponse<>();
        Optional<ProductBox> productBox = repository.findByProductId(productId);
        if (productBox.isEmpty())
            return response.of(HttpStatus.NOT_FOUND, "Nenhuma caixa de produtos encontrada para o id do produto informado");
        return response.of(HttpStatus.OK, "Caixa de produtos encontrada", productBox.get());
    }
}
