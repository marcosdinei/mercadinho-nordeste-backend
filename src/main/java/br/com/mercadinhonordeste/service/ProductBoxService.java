package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.ProductBox;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.repository.ProductBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBoxService {
    private final ProductBoxRepository repository;

    public ApiResponse<ProductBox> saveBox(ProductBox productBox) {
        ApiResponse<ProductBox> response = new ApiResponse<>();
        return response.of(HttpStatus.CREATED, "Caixa de produtos cadastrada com sucesso", repository.save(productBox));
    }

    public ApiResponse<ProductBox> updateBox(ProductBox productBox) {
        ApiResponse<ProductBox> response = new ApiResponse<>();
        if (!repository.existsById(productBox.getId()))
            return response.of(HttpStatus.NOT_FOUND, "Caixa de produtos não encontrada com o id inforado");
        return response.of(HttpStatus.OK, "Caixa de produtos atualizada com sucesso", repository.save(productBox));
    }

    public ApiResponse<ProductBox> getBoxByProductId(Integer productId) {
        ApiResponse<ProductBox> response = new ApiResponse<>();
        Optional<ProductBox> productBox = repository.findByProductId(productId);
        if (productBox.isEmpty())
            return response.of(HttpStatus.NOT_FOUND, "Nenhuma caixa de produtos encontrada para o id do produto informado");
        return response.of(HttpStatus.OK, "Caixa de produtos encontrada", productBox.get());
    }
}
