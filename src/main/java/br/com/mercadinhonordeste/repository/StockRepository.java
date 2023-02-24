package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Product;
import br.com.mercadinhonordeste.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Optional<Stock> findByProduct(Product product);
}
