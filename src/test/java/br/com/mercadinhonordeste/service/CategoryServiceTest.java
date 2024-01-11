package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Category;
import br.com.mercadinhonordeste.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService service;
    @Mock
    private CategoryRepository repository;
    @Mock
    private Category category;
    @Mock
    private List<Category> categories;

    @Test
    void shouldSaveNewCategory() {
        service.saveCategory(category);

        then(repository).should().save(category);
    }

    @Test
    void shouldListCategoriesByName() {
        String name = "abc";
        given(repository.findByNameContainingIgnoreCase(Mockito.anyString(), Mockito.any())).willReturn(categories);

        service.listCategories(name);

        then(repository).should().findByNameContainingIgnoreCase(Mockito.anyString(), Mockito.any());
    }
}