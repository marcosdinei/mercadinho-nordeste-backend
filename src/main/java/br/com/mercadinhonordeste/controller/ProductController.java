package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.Product;
import br.com.mercadinhonordeste.exception.NotFoundExeption;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.Item;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.service.ProductService;
import br.com.mercadinhonordeste.service.criteria.ProductCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
    private final ProductService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<Product>> saveProduct(@RequestBody Product product) {
        ApiResponse<Product> response = new ApiResponse<>();
        response.of(HttpStatus.CREATED, "Produto cadastrado com sucesso", service.saveProduct(product));
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<Product>> updateProduct(@RequestBody Product product) {
        ApiResponse<Product> response = new ApiResponse<>();
        try {
            response.of(HttpStatus.OK, "Produto atualizado com sucesso", service.updateProduct(product));
        } catch (NotFoundExeption e) {
            response.of(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse<Item>> getProductByCode(@PathVariable String code) {
        ApiResponse<Item> response = new ApiResponse<>();
        try {
            response.of(HttpStatus.OK, "Produto encontrado com sucesso", service.getProductByCode(code));
        } catch (NotFoundExeption e) {
            response.of(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<PaginatedData<Product>>> listProducts(
            ProductCriteria criteria, @PageableDefault() Pageable pageable) {
        ApiResponse<PaginatedData<Product>> response = new ApiResponse<>();
        response.of(
                HttpStatus.OK,
                "Produto(s) encontrado(s)",
                service.listProducts(criteria, pageable));
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
