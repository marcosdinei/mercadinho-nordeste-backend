package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Cash;
import br.com.mercadinhonordeste.exception.NotFoundExeption;
import br.com.mercadinhonordeste.repository.CashRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CashServiceTest {
    @InjectMocks
    private CashService service;
    @Mock
    private CashRepository repository;
    @Mock
    private Cash cash;

    @Test
    void shouldInitNewCash() {
        given(repository.save(cash)).willReturn(cash);

        service.initCash(cash);

        then(repository).should().save(cash);
    }

    @Test
    void shouldUpdateCash() {
        given(repository.existsById(cash.getId())).willReturn(true);

        service.updateCash(cash);

        then(repository).should().save(cash);
    }

    @Test
    void shouldNotFindCashByIdOnUpdate() {
        given(repository.existsById(cash.getId())).willReturn(false);

        assertThrows(NotFoundExeption.class, () -> service.updateCash(cash));
        then(repository).should().existsById(cash.getId());
    }
}