package br.com.mercadinhonordeste.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(schema = "compras", name = "compra")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_total")
    private Double totalValue;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fornecedor_id")
    private Supplier supplier;

    @Column(name = "data")
    private String date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<PurchasedItems> items;
}
