package br.com.mercadinhonordeste.model;

import br.com.mercadinhonordeste.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Item {
    Product product;
    Boolean box;
}
