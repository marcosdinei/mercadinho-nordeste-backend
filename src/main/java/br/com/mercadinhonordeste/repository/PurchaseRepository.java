package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>, JpaSpecificationExecutor<Purchase> {
    @Query("SELECT SUM(p.totalValue) FROM Purchase p WHERE p.date >= :initialDate AND p.date <= :finalDate")
    Double sumTotalValue(
            @Param("initialDate") String initialDate,
            @Param("finalDate") String finalDate
    );
}
