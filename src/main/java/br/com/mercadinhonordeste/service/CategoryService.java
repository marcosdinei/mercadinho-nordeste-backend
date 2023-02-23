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

    public ApiResponse<Category> saveCategory(Category category) {
        ApiResponse<Category> response = new ApiResponse<>();
        return response.of(HttpStatus.CREATED, "Categoria cadastrada com sucesso", repository.save(category));
    }

    public ApiResponse<List<Category>> listCategories(String name) {
        ApiResponse<List<Category>> response = new ApiResponse<>();
        List<Category> categories = repository.findByNameContainingIgnoreCase(name, Sort.by(Sort.Direction.ASC, "name"));
        return response.of(HttpStatus.OK, "Categorias encontradas", categories);
    }
}
