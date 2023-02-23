package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.Category;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("category")
public class CategoryController {
    private final CategoryService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<Category>> saveCategory(@RequestBody Category category) {
        ApiResponse<Category> response = service.saveCategory(category);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Category>>> listCategories(String name) {
        ApiResponse<List<Category>> response = service.listCategories(name);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
