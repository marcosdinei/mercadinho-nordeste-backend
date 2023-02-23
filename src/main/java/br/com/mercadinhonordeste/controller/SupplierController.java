package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.Supplier;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("supplier")
public class SupplierController {
    private final SupplierService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<Supplier>> saveSupplier(@RequestBody Supplier supplier) {
        ApiResponse<Supplier> response = service.saveSupplier(supplier);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Supplier>>> listSuppliers(String name) {
        ApiResponse<List<Supplier>> response = service.listSuppliers(name);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
