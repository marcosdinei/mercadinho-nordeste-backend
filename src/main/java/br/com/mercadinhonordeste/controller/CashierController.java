package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.Cashier;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.service.CashierService;
import br.com.mercadinhonordeste.service.criteria.CashierCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("cashier")
public class CashierController {
    private final CashierService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<Cashier>> initCashier(@RequestBody Cashier cashier) {
        ApiResponse<Cashier> response = service.initCashier(cashier);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<Cashier>> updateCashier(@RequestBody Cashier cashier) {
        ApiResponse<Cashier> response = service.updateCashier(cashier);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<PaginatedData<Cashier>>> listCashiers(
            CashierCriteria criteria, @PageableDefault() Pageable pageable) {
        ApiResponse<PaginatedData<Cashier>> response = service.listCashiers(criteria, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
