package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Cash;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.model.Pagination;
import br.com.mercadinhonordeste.repository.CashRepository;
import br.com.mercadinhonordeste.service.criteria.CashCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashService {
    private final CashRepository repository;

    public ApiResponse<Cash> initCash(Cash cash) {
        ApiResponse<Cash> response = new ApiResponse<>();
        cash.setCurrentValue(cash.getInitialValue());
        cash.setInProgress(true);
        return response.of(HttpStatus.CREATED, "Caixa iniciado", repository.save(cash));
    }

    public ApiResponse<Cash> updateCash(Cash cash) {
        ApiResponse<Cash> response = new ApiResponse<>();
        if (!repository.existsById(cash.getId()))
            return response.of(HttpStatus.NOT_FOUND, "Caixa não encontrado");
        return response.of(HttpStatus.OK, "Caixa atualizado com sucesso", repository.save(cash));
    }

    public ApiResponse<PaginatedData<Cash>> listCashs(CashCriteria criteria, Pageable pageable) {
        ApiResponse<PaginatedData<Cash>> response = new ApiResponse<>();
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "date")
        );
        Page<Cash> cashPage = repository.findAll(createSpecification(criteria), pageable);
        final Pagination pagination = Pagination.from(cashPage, pageable);
        return response.of(
                HttpStatus.OK,
                pagination.getTotalNumberOfElements().toString().concat(" caixa(s) encontrado(s)"),
                new PaginatedData<>(cashPage.getContent(), pagination));
    }

    private Specification<Cash> createSpecification(CashCriteria criteria) {
        Specification<Cash> specification = Specification.where(null);

        if (criteria.getInitialDate() != null)
            specification = specification.and(CashCriteria.filterByInitialDate(criteria.getInitialDate()));

        if (criteria.getFinalDate() != null)
            specification = specification.and(CashCriteria.filterByFinalDate(criteria.getFinalDate()));

        if (criteria.getInProgress() != null)
            specification = specification.and(CashCriteria.filterByInProgressCashier(criteria.getInProgress()));

        return specification;
    }
}
