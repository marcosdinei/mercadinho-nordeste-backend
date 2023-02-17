package br.com.mercadinhonordeste.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginatedData<T> {
    private List<T> content;
    private Pagination pagination;
}
