package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.Cash;
import br.com.mercadinhonordeste.entity.Product;
import br.com.mercadinhonordeste.exception.NotFoundExeption;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.service.CashService;
import br.com.mercadinhonordeste.service.criteria.CashCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("cash")
public class CashController {
    private final CashService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<Cash>> initCash(@RequestBody Cash cash) {
        ApiResponse<Cash> response = new ApiResponse<>();
        response.of(HttpStatus.CREATED, "Caixa iniciado", service.initCash(cash));
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<Cash>> updateCash(@RequestBody Cash cash) {
        ApiResponse<Cash> response = new ApiResponse<>();
        try {
            response.of(HttpStatus.OK, "Caixa atualizado com sucesso", service.updateCash(cash));
        } catch (NotFoundExeption e) {
            response.of(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<PaginatedData<Cash>>> listCashs(
            CashCriteria criteria, @PageableDefault() Pageable pageable) {
        ApiResponse<PaginatedData<Cash>> response = new ApiResponse<>();
        response.of(
                HttpStatus.OK,
                "Caixas encontrados",
                service.listCashs(criteria, pageable));
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
