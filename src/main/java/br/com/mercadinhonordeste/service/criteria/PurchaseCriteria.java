package br.com.mercadinhonordeste.service.criteria;

import br.com.mercadinhonordeste.entity.Purchase;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@Data
@NoArgsConstructor
public class PurchaseCriteria {
    private Double minValue;
    private Double maxValue;
    private String initialDate;
    private String finalDate;
    private Integer supplier;

    public static Specification<Purchase> filterByMinValue(Double minValue) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("totalValue"), minValue);
    }

    public static Specification<Purchase> filterByMaxValue(Double maxValue) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("totalValue"), maxValue);
    }

    public static Specification<Purchase> filterByInitialDate(String initialDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("date"), initialDate);
    }

    public static Specification<Purchase> filterByFinalDate(String finalDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("date"), finalDate);
    }

    public static Specification<Purchase> filterBySupplierId(Integer supplierId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("supplier").get("id"), supplierId);
    }
}
