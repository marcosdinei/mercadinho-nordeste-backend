package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.Report;
import br.com.mercadinhonordeste.repository.ClientPaymentRepository;
import br.com.mercadinhonordeste.repository.ClientRepository;
import br.com.mercadinhonordeste.repository.PurchaseRepository;
import br.com.mercadinhonordeste.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final PurchaseRepository purchaseRepository;
    private final SaleRepository saleRepository;
    private final ClientPaymentRepository clientPaymentRepository;
    private final ClientRepository clientRepository;

    public ApiResponse<Report> getReport(String initialDate, String finalDate) {
        ApiResponse<Report> response = new ApiResponse<>();

        Double spent = purchaseRepository.sumTotalValue(initialDate, finalDate);
        Double sales = saleRepository.sumValuePaid(initialDate, finalDate);
        Double payments = clientPaymentRepository.sumAmountPaid(initialDate, finalDate);
        Double toReceive = clientRepository.sumAmountToPay();

        if (spent == null) spent = 0.0;
        if (sales == null) sales = 0.0;
        if (payments == null) payments = 0.0;
        Double revenue = sales + payments;
        Double balance = revenue - spent;

        Report report = new Report(initialDate, finalDate, spent, revenue, balance, toReceive);
        return response.of(HttpStatus.OK, "Relatório gerado com sucesso", report);
    }
}
