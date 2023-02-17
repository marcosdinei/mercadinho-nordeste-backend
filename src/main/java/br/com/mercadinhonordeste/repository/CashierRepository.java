package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CashierRepository extends JpaRepository<Cashier, Integer>, JpaSpecificationExecutor<Cashier> {
}
