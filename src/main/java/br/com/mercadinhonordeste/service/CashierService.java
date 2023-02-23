package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Cashier;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.model.Pagination;
import br.com.mercadinhonordeste.repository.CashierRepository;
import br.com.mercadinhonordeste.service.criteria.CashierCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CashierService {
    private final CashierRepository repository;

    public ApiResponse<Cashier> initCashier(Cashier cashier) {
        ApiResponse<Cashier> response = new ApiResponse<>();
        cashier.setCurrentValue(cashier.getInitialValue());
        cashier.setInProgress(true);
        return response.of(HttpStatus.CREATED, "Caixa iniciado", repository.save(cashier));
    }

    public ApiResponse<Cashier> closeCashier(Cashier cashier) {
        ApiResponse<Cashier> response = new ApiResponse<>();
        if (!repository.existsById(cashier.getId()))
            return response.of(HttpStatus.NOT_FOUND, "Caixa não encontrado");
        return response.of(HttpStatus.OK, "Caixa encerrado com sucesso", repository.save(cashier));
    }

    public ApiResponse<PaginatedData<Cashier>> listCashiers(CashierCriteria criteria, Pageable pageable) {
        ApiResponse<PaginatedData<Cashier>> response = new ApiResponse<>();
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "date")
        );
        Page<Cashier> cashiers = repository.findAll(createSpecification(criteria), pageable);
        final Pagination pagination = Pagination.from(cashiers, pageable);
        return response.of(
                HttpStatus.OK,
                pagination.getTotalNumberOfElements().toString().concat(" caixa(s) encontrado(s)"),
                new PaginatedData<>(cashiers.getContent(), pagination));
    }

    private Specification<Cashier> createSpecification(CashierCriteria criteria) {
        Specification<Cashier> specification = Specification.where(null);

        if (criteria.getInitialDate() != null)
            specification = specification.and(CashierCriteria.filterByInitialDate(criteria.getInitialDate()));

        if (criteria.getFinalDate() != null)
            specification = specification.and(CashierCriteria.filterByFinalDate(criteria.getFinalDate()));

        if (criteria.getInProgress() != null)
            specification = specification.and(CashierCriteria.filterByInProgressCashier(criteria.getInProgress()));

        return specification;
    }
}
