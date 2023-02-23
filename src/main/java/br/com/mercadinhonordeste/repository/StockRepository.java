package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
