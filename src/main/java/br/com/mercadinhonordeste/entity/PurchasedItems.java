package br.com.mercadinhonordeste.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "caixa_produto")
    private Boolean isBox;

    @Column(name = "quantidade_item")
    private Double quantityItem;

    @Column(name = "valor_unitario")
    private Double singleValue;
}
