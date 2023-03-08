package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.Cash;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.service.CashService;
import br.com.mercadinhonordeste.service.criteria.CashCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("cashier")
public class CashierController {
    private final CashService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<Cash>> initCashier(@RequestBody Cash cashier) {
        ApiResponse<Cash> response = service.initCashier(cashier);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<Cash>> updateCashier(@RequestBody Cash cashier) {
        ApiResponse<Cash> response = service.updateCashier(cashier);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<PaginatedData<Cash>>> listCashiers(
            CashCriteria criteria, @PageableDefault() Pageable pageable) {
        ApiResponse<PaginatedData<Cash>> response = service.listCashiers(criteria, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
