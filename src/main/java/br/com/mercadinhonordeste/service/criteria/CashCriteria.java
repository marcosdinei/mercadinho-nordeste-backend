package br.com.mercadinhonordeste.service.criteria;

import br.com.mercadinhonordeste.entity.Cash;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@Data
@NoArgsConstructor
public class CashCriteria {
    private String initialDate;
    private String finalDate;
    private Boolean inProgress;

    public static Specification<Cash> filterByInitialDate(String initialDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("date"), initialDate);
    }

    public static Specification<Cash> filterByFinalDate(String finalDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("date"), finalDate);
    }

    public static Specification<Cash> filterByInProgressCashier(Boolean inProgress) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("inProgress"), inProgress);
    }
}
