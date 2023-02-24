package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.Sale;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.service.SaleService;
import br.com.mercadinhonordeste.service.criteria.SaleCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("sale")
public class SaleController {
    private final SaleService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<Sale>> saveSale(@RequestBody Sale sale) {
        ApiResponse<Sale> response = service.saveSale(sale);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteSale(@PathVariable Integer id) {
        ApiResponse<?> response = service.deleteSale(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<PaginatedData<Sale>>> listSales(
            SaleCriteria criteria, @PageableDefault() Pageable pageable) {
        ApiResponse<PaginatedData<Sale>> response = service.listSales(criteria, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
