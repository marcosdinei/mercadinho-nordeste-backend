package br.com.mercadinhonordeste.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "vendas", name = "itens_vendidos")
public class SoldItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "venda_id")
    private Sale sale;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private Product product;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "caixa_produto_id")
    private ProductBox productBox;

    @Column(name = "quantidade_produto")
    private Double quantityProduct;

    @Column(name = "quantidade_caixa")
    private Double quantityProductBox;

    @Column(name = "valor_unitario")
    private Double single_value;
}
