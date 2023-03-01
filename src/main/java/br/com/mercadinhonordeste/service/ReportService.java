package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Purchase;
import br.com.mercadinhonordeste.entity.Sale;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.Report;
import br.com.mercadinhonordeste.repository.PurchaseRepository;
import br.com.mercadinhonordeste.repository.SaleRepository;
import br.com.mercadinhonordeste.service.criteria.PurchaseCriteria;
import br.com.mercadinhonordeste.service.criteria.SaleCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final PurchaseRepository purchaseRepository;
    private final SaleRepository saleRepository;

    public ApiResponse<Report> getReport(String initialDate, String finalDate) {
        ApiResponse<Report> response = new ApiResponse<>();

        List<Purchase> purchases = purchaseRepository.findAll(
                createPurchaseSpecification(initialDate, finalDate),
                Sort.by(Sort.Direction.DESC, "date")
        );
        List<Sale> sales = saleRepository.findAll(
                createSaleSpecification(initialDate, finalDate),
                Sort.by(Sort.Direction.DESC, "date")
        );

        AtomicReference<Double> spent = new AtomicReference<>(0.0);
        purchases.forEach(purchase -> {
            spent.updateAndGet(v -> v - purchase.getTotalValue());
        });

        AtomicReference<Double> revenue = new AtomicReference<>(0.0);
        sales.forEach(sale -> {
            revenue.updateAndGet(v -> v + sale.getTotalValue());
        });

        Double balance = spent.get() + revenue.get();
        Report report = new Report(initialDate, finalDate, spent.get(), revenue.get(), balance);

        return response.of(HttpStatus.OK, "Relatório gerado com sucesso", report);
    }

    private Specification<Purchase> createPurchaseSpecification(String initialDate, String finalDate) {
        Specification<Purchase> specification = Specification.where(null);

        if (initialDate != null)
            specification = specification.and(PurchaseCriteria.filterByInitialDate(initialDate));

        if (finalDate != null)
            specification = specification.and(PurchaseCriteria.filterByFinalDate(finalDate));

        return specification;
    }

    private Specification<Sale> createSaleSpecification(String initialDate, String finalDate) {
        Specification<Sale> specification = Specification.where(null);

        if (initialDate != null)
            specification = specification.and(SaleCriteria.filterByInitialDate(initialDate));

        if (finalDate != null)
            specification = specification.and(SaleCriteria.filterByFinalDate(finalDate));

        return specification;
    }
}
