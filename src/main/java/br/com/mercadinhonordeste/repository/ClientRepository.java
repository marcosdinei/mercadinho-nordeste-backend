package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Page<Client> findByNameContainingIgnoreCase(String name, Pageable pageable);
    @Query("SELECT SUM(c.amountToPay) FROM Client c")
    Double sumAmountToPay();
}
