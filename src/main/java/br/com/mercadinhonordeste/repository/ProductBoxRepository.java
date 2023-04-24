package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.ProductBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductBoxRepository extends JpaRepository<ProductBox, Integer>, JpaSpecificationExecutor<ProductBox> {
    Optional<ProductBox> findByProductId(Integer productId);
    Optional<ProductBox> findByCode(String code);
}
