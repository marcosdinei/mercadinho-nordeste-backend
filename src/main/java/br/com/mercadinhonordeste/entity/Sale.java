package br.com.mercadinhonordeste.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(schema = "vendas", name = "venda")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_total")
    private Double totalValue;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Client client;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "forma_pagamento_id")
    private Payment payment;

    @Column(name = "data")
    private String date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<SoldItems> items;
}
