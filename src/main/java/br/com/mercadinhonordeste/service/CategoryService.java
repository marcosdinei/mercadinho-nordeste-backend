package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Category;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public List<Category> listCategories(String name) {
        return repository.findByNameContainingIgnoreCase(name, Sort.by(Sort.Direction.ASC, "name"));
    }
}
