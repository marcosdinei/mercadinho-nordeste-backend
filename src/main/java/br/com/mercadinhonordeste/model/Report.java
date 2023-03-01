package br.com.mercadinhonordeste.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private String initialDate;
    private String finalDate;
    private Double spent;
    private Double revenue;
    private Double balance;
    private Double toReceive;
}
