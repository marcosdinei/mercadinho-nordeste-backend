package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.Report;
import br.com.mercadinhonordeste.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("report")
public class ReportController {
    private final ReportService service;

    @GetMapping()
    public ResponseEntity<ApiResponse<Report>> getReport(
            @RequestParam String initialDate, @RequestParam String finalDate) {
        ApiResponse<Report> response = service.getReport(initialDate, finalDate);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
