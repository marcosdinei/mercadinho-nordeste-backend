package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Product;
import br.com.mercadinhonordeste.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByProduct(Product product);
}
