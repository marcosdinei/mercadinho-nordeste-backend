package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Purchase;
import br.com.mercadinhonordeste.entity.Stock;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.model.Pagination;
import br.com.mercadinhonordeste.repository.PurchaseRepository;
import br.com.mercadinhonordeste.repository.StockRepository;
import br.com.mercadinhonordeste.service.criteria.PurchaseCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository repository;
    private final StockRepository stockRepository;

    public ApiResponse<Purchase> savePurchase(Purchase purchase) {
        ApiResponse<Purchase> response = new ApiResponse<>();
        Purchase savedPurchase = repository.save(purchase);
        savedPurchase.getItems().forEach(item -> {
            if (item.getProduct() != null) {
                Stock stock = stockRepository.findByProduct(item.getProduct()).get();
                stock.setQuantity(stock.getQuantity() + item.getQuantityProduct());
                stockRepository.save(stock);
            } else if (item.getProductBox() != null) {
                Stock stock = stockRepository.findByProduct(item.getProductBox().getProduct()).get();
                stock.setQuantity(stock.getQuantity() + (item.getQuantityProductBox() * item.getProductBox().getQuantityProduct()));
                stockRepository.save(stock);
            }
        });
        return response.of(HttpStatus.CREATED, "Compra cadastrada com sucesso", savedPurchase);
    }

    public ApiResponse<?> deletePurchase(Integer id) {
        ApiResponse<?> response = new ApiResponse<>();
        if (!repository.existsById(id))
            return response.of(HttpStatus.NOT_FOUND, "Nenhuma compra encontrada com o id informado");
        repository.deleteById(id);
        return response.of(HttpStatus.OK, "Compra deletada com sucesso");
    }

    public ApiResponse<PaginatedData<Purchase>> listPurchase(PurchaseCriteria criteria, Pageable pageable) {
        ApiResponse<PaginatedData<Purchase>> response = new ApiResponse<>();
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "date")
        );
        Page<Purchase> purchases = repository.findAll(createSpecification(criteria), pageable);
        final Pagination pagination = Pagination.from(purchases, pageable);
        return response.of(
                HttpStatus.OK,
                pagination.getTotalNumberOfElements().toString().concat(" compra(s) encontrada(s)"),
                new PaginatedData<>(purchases.getContent(), pagination));
    }

    private Specification<Purchase> createSpecification(PurchaseCriteria criteria) {
        Specification<Purchase> specification = Specification.where(null);

        if (criteria.getMinValue() != null)
            specification = specification.and(PurchaseCriteria.filterByMinValue(criteria.getMinValue()));

        if (criteria.getMaxValue() != null)
            specification = specification.and(PurchaseCriteria.filterByMaxValue(criteria.getMaxValue()));

        if (criteria.getInitialDate() != null)
            specification = specification.and(PurchaseCriteria.filterByInitialDate(criteria.getInitialDate()));

        if (criteria.getFinalDate() != null)
            specification = specification.and(PurchaseCriteria.filterByFinalDate(criteria.getFinalDate()));

        if (criteria.getSupplier() != null)
            specification = specification.and(PurchaseCriteria.filterBySupplierId(criteria.getSupplier()));

        return specification;
    }
}
