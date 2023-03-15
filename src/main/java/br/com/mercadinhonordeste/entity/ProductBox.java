package br.com.mercadinhonordeste.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "produtos", name = "caixa_produto")
public class ProductBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    @JsonBackReference
    private Product product;

    @Column(name = "quantidade_produto")
    private Double quantityProduct;

    @Column(name = "preco_venda")
    private Double price;

    @Column(name = "codigo")
    private String code;
}
