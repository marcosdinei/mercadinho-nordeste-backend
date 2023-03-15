package br.com.mercadinhonordeste.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "produtos", name = "produto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao")
    private String description;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Category category;

    @Column(name = "preco_venda")
    private Double price;

    @Column(name = "codigo")
    private String code;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "caixa_produto_id")
    @JsonManagedReference
    private ProductBox box;
}
