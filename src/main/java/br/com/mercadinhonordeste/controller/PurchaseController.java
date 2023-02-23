package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.Purchase;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.service.PurchaseService;
import br.com.mercadinhonordeste.service.criteria.PurchaseCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("purchase")
public class PurchaseController {
    private final PurchaseService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<Purchase>> savePurchase(@RequestBody Purchase purchase) {
        ApiResponse<Purchase> response = service.savePurchase(purchase);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deletePurchase(@PathVariable Integer id) {
        ApiResponse<?> response = service.deletePurchase(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<PaginatedData<Purchase>>> listPurchases(
            PurchaseCriteria criteria, @PageableDefault() Pageable pageable) {
        ApiResponse<PaginatedData<Purchase>> response = service.listPurchase(criteria, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
