package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Supplier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findByNameContainingIgnoreCase(String name, Sort sort);
}
