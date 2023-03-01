package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleRepository extends JpaRepository<Sale, Integer>, JpaSpecificationExecutor<Sale> {
    @Query("SELECT SUM(s.valuePaid) FROM Sale s WHERE s.payment.id != 5 AND s.date >= :initialDate AND s.date <= :finalDate")
    Double sumValuePaid(
            @Param("initialDate") String initialDate,
            @Param("finalDate") String finalDate
    );
}
