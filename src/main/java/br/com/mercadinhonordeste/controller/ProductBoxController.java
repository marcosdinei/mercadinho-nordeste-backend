package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.ProductBox;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.service.ProductBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("box")
public class ProductBoxController {
    private final ProductBoxService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<ProductBox>> saveBox(@RequestBody ProductBox productBox) {
        ApiResponse<ProductBox> response = service.saveBox(productBox);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<ProductBox>> updateBox(@RequestBody ProductBox productBox) {
        ApiResponse<ProductBox> response = service.updateBox(productBox);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<ProductBox>> getBoxByProductId(Integer productId) {
        ApiResponse<ProductBox> response = service.getBoxByProductId(productId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
