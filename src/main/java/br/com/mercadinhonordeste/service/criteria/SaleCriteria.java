package br.com.mercadinhonordeste.service.criteria;

import br.com.mercadinhonordeste.entity.Sale;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@Data
@NoArgsConstructor
public class SaleCriteria {
    private Double minValue;
    private Double maxValue;
    private String initialDate;
    private String finalDate;
    private Integer payment;
    private Integer client;

    public static Specification<Sale> filterByMinValue(Double minValue) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("totalValue"), minValue);
    }

    public static Specification<Sale> filterByMaxValue(Double maxValue) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("totalValue"), maxValue);
    }

    public static Specification<Sale> filterByInitialDate(String initialDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("date"), initialDate);
    }

    public static Specification<Sale> filterByFinalDate(String finalDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("date"), finalDate);
    }

    public static Specification<Sale> filterByPaymentId(Integer paymentId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("payment").get("id"), paymentId);
    }

    public static Specification<Sale> filterByClientId(Integer clientId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("client").get("id"), clientId);
    }
}
