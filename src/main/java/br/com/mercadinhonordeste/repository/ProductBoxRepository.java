package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.ProductBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductBoxRepository extends JpaRepository<ProductBox, Integer> {
    Optional<ProductBox> findByProductId(Integer id);
}
