package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.Product;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.service.ProductService;
import br.com.mercadinhonordeste.service.criteria.ProductCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
    private final ProductService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<Product>> saveProduct(@RequestBody Product product) {
        ApiResponse<Product> response = service.saveProduct(product);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<Product>> updateProduct(@RequestBody Product product) {
        ApiResponse<Product> response = service.updateProduct(product);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse<Product>> getProductByCode(@PathVariable String code) {
        ApiResponse<Product> response = service.getProductByCode(code);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<PaginatedData<Product>>> listProducts(
            ProductCriteria criteria, @PageableDefault() Pageable pageable) {
        ApiResponse<PaginatedData<Product>> response = service.listProducts(criteria, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
