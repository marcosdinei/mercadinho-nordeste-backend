package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CashRepository extends JpaRepository<Cash, Integer>, JpaSpecificationExecutor<Cash> {
    List<Cash> findByInProgress(Boolean inProgress);
}
