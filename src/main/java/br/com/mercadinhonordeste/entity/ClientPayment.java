package br.com.mercadinhonordeste.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "vendas", name = "pagamento_cliente")
public class ClientPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Client client;

    @Column(name = "data")
    private String date;

    @Column(name = "valor_pago")
    private Double amountPaid;
}
