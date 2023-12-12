package br.com.mercadinhonordeste.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "vendas", name = "itens_vendidos")
public class SoldItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venda_id")
    @JsonBackReference
    private Sale sale;

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
