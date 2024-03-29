package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByCode(String code);
    Optional<Product> findByBoxCode(String code);
}
