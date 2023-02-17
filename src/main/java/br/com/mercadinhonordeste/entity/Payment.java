package br.com.mercadinhonordeste.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "tipos", name = "forma_pagamento")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String name;
}
