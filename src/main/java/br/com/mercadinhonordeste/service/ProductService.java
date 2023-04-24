package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Product;
import br.com.mercadinhonordeste.entity.ProductBox;
import br.com.mercadinhonordeste.entity.Stock;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.model.Pagination;
import br.com.mercadinhonordeste.repository.ProductBoxRepository;
import br.com.mercadinhonordeste.repository.ProductRepository;
import br.com.mercadinhonordeste.repository.StockRepository;
import br.com.mercadinhonordeste.service.criteria.ProductCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final StockRepository stockRepository;
    private final ProductBoxRepository boxRepository;

    public ApiResponse<Product> saveProduct(Product product) {
        ApiResponse<Product> response = new ApiResponse<>();
        Product productSaved = repository.save(product);
        stockRepository.save(new Stock(null, productSaved, 0.0));
        return response.of(HttpStatus.CREATED, "Produto cadastrado com sucesso", productSaved);
    }

    public ApiResponse<Product> updateProduct(Product product) {
        ApiResponse<Product> response = new ApiResponse<>();

        if (!repository.existsById(product.getId()))
            return response.of(HttpStatus.NOT_FOUND, "Produto não encontrado com o id informado");

        if (product.getBox() == null) {
            Optional<ProductBox> box = boxRepository.findByProductId(product.getId());
            if (box.isPresent())
                boxRepository.delete(box.get());
        }

        return response.of(HttpStatus.OK, "Produto atualizado com sucesso", repository.save(product));
    }

    public ApiResponse<Product> getProductByCode(String code) {
        ApiResponse<Product> response = new ApiResponse<>();
        Optional<Product> product = repository.findByCode(code);
        if (product.isEmpty())
            return response.of(HttpStatus.NOT_FOUND, "Produto não encontrado com o código informado");
        return response.of(HttpStatus.OK, "Produto encontrado com sucesso", product.get());
    }

    public ApiResponse<PaginatedData<Product>> listProducts(ProductCriteria criteria, Pageable pageable) {
        ApiResponse<PaginatedData<Product>> response = new ApiResponse<>();
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "description")
        );
        Page<Product> products = repository.findAll(createSpecification(criteria), pageable);
        final Pagination pagination = Pagination.from(products, pageable);
        return response.of(
                HttpStatus.OK,
                pagination.getTotalNumberOfElements().toString().concat(" produto(s) encontrado(s)"),
                new PaginatedData<>(products.getContent(), pagination));
    }

    private Specification<Product> createSpecification(ProductCriteria criteria) {
        Specification<Product> specification = Specification.where(null);

        if (criteria.getDescription() != null)
            specification = specification.and(ProductCriteria.searchByDescription(criteria.getDescription()));

        if (criteria.getCategory() != null)
            specification = specification.and(ProductCriteria.filterByCategoryId(criteria.getCategory()));

        if (criteria.getMaxPrice() != null)
            specification = specification.and(ProductCriteria.filterByMaxPrice(criteria.getMaxPrice()));

        if (criteria.getMinPrice() != null)
            specification = specification.and(ProductCriteria.filterByMinPrice(criteria.getMinPrice()));

        return specification;
    }
}
