package br.com.mercadinhonordeste.repository;

import br.com.mercadinhonordeste.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Page<Client> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
