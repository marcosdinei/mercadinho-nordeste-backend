package br.com.mercadinhonordeste.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "caixa", name = "caixa")
public class Cash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data")
    private String date;

    @Column(name = "valor_inicial")
    private Double initialValue;

    @Column(name = "valor_atual")
    private Double currentValue;

    @Column(name = "em_andamento")
    private Boolean inProgress;
}
