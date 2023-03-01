package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.ClientPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientPaymentRepository extends JpaRepository<ClientPayment, Integer> {
    @Query("SELECT SUM(cp.amountPaid) FROM ClientPayment cp WHERE cp.date >= :initialDate AND cp.date <= :finalDate")
    Double sumAmountPaid(
            @Param("initialDate") String initialDate,
            @Param("finalDate") String finalDate
    );
}
