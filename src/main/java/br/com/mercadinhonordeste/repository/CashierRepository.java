package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CashierRepository extends JpaRepository<Cashier, Integer>, JpaSpecificationExecutor<Cashier> {
    List<Cashier> findByInProgress(Boolean inProgress);
}
