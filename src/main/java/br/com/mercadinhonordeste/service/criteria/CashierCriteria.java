package br.com.mercadinhonordeste.service.criteria;

import br.com.mercadinhonordeste.entity.Cashier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@Data
@NoArgsConstructor
public class CashierCriteria {
    private String initialDate;
    private String finalDate;
    private Boolean inProgress;

    public static Specification<Cashier> filterByInitialDate(String initialDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("date"), initialDate);
    }

    public static Specification<Cashier> filterByFinalDate(String finalDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("date"), finalDate);
    }

    public static Specification<Cashier> filterByInProgressCashier(Boolean inProgress) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("inProgress"), inProgress);
    }
}
