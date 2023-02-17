package br.com.mercadinhonordeste.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "produtos", name = "estoque")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private Product product;

    @Column(name = "quantidade")
    private Double quantity;
}
