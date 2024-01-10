package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Product;
import br.com.mercadinhonordeste.entity.ProductBox;
import br.com.mercadinhonordeste.entity.Stock;
import br.com.mercadinhonordeste.exception.NotFoundExeption;
import br.com.mercadinhonordeste.model.Item;
import br.com.mercadinhonordeste.repository.ProductBoxRepository;
import br.com.mercadinhonordeste.repository.ProductRepository;
import br.com.mercadinhonordeste.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository repository;
    @Mock
    private StockRepository stockRepository;
    @Mock
    private ProductBoxRepository boxRepository;
    @Mock
    private Product product;
    @Mock
    private ProductBox box;
    @Captor
    private ArgumentCaptor<Stock> stockCaptor;

    @Test
    void shouldSaveNewProductAndNewStock() {
        given(repository.save(product)).willReturn(product);

        service.saveProduct(product);

        then(repository).should().save(product);
        then(stockRepository).should().save(stockCaptor.capture());
        assertEquals(product, stockCaptor.getValue().getProduct());
    }

    @Test
    void shouldUpdateProductAndBox() {
        given(repository.existsById(product.getId())).willReturn(true);
        given(boxRepository.findByProductId(product.getId())).willReturn(Optional.of(box));

        service.updateProduct(product);

        then(boxRepository).should().delete(box);
        then(repository).should().save(product);
    }

    @Test
    void shouldNotFindProductByIdOnUpdate() {
        given(repository.existsById(product.getId())).willReturn(false);

        assertThrows(NotFoundExeption.class, () -> service.updateProduct(product));
        then(repository).should().existsById(product.getId());
    }

    @Test
    void shouldReturnProductByCode() {
        given(repository.findByCode(Mockito.anyString())).willReturn(Optional.of(product));

        Item item = service.getProductByCode(Mockito.anyString());

        then(repository).should().findByCode(Mockito.anyString());
        assertEquals(item.getProduct(), product);
        assertFalse(item.getBox());
    }

    @Test
    void shouldReturnProductBoxByCode() {
        given(repository.findByCode(Mockito.anyString())).willReturn(Optional.empty());
        given(repository.findByBoxCode(Mockito.anyString())).willReturn(Optional.of(product));

        Item item = service.getProductByCode(Mockito.anyString());

        then(repository).should().findByCode(Mockito.anyString());
        assertEquals(item.getProduct(), product);
        assertTrue(item.getBox());
    }

    @Test
    void shouldNotFindProductByCode() {
        given(repository.findByCode(Mockito.anyString())).willReturn(Optional.empty());
        given(repository.findByBoxCode(Mockito.anyString())).willReturn(Optional.empty());

        assertThrows(NotFoundExeption.class, () -> service.getProductByCode(Mockito.anyString()));
        then(repository).should().findByCode(Mockito.anyString());
    }
}