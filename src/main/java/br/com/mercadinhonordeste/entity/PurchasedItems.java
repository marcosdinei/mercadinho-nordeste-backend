package br.com.mercadinhonordeste.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "compras", name = "itens_comprados")
public class PurchasedItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "compra_id")
    @JsonBackReference
    private Purchase purchase;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private Product product;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "caixa_produto_id")
    private ProductBox productBox;

    @Column(name = "quantidade_produto")
    private Double quantityProduct;

    @Column(name = "quantidade_caixa")
    private Double quantityProductBox;

    @Column(name = "valor_unitario")
    private Double singleValue;
}
