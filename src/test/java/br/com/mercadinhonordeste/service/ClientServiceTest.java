package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Client;
import br.com.mercadinhonordeste.entity.ClientPayment;
import br.com.mercadinhonordeste.exception.NotFoundExeption;
import br.com.mercadinhonordeste.repository.ClientPaymentRepository;
import br.com.mercadinhonordeste.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @InjectMocks
    private ClientService service;
    @Mock
    private ClientRepository repository;
    @Mock
    private ClientPaymentRepository paymentRepository;
    @Mock
    private Client client;
    @Mock
    private ClientPayment payment;
    @Captor
    private ArgumentCaptor<Client> clientCaptor;

    @Test
    void shouldSaveNewClient() {
        service.saveClient(client);

        then(repository).should().save(client);
    }

    @Test
    void shouldUpdateClient() {
        given(repository.existsById(Mockito.anyInt())).willReturn(true);

        service.updateClient(client);

        then(repository).should().save(client);
    }

    @Test
    void shouldNotFindClientOnUpdate() {
        given(repository.existsById(Mockito.anyInt())).willReturn(false);

        assertThrows(NotFoundExeption.class, () -> service.updateClient(client));
        then(repository).should().existsById(client.getId());
    }

    @Test
    void shouldSavePaymentAndUpdateClient() {
        given(payment.getClient()).willReturn(client);
        given(repository.findById(Mockito.anyInt())).willReturn(Optional.of(client));

        service.savePayment(payment);

        then(repository).should().findById(payment.getClient().getId());
        then(repository).should().save(clientCaptor.capture());
        then(paymentRepository).should().save(payment);
        assertEquals(payment.getClient(), clientCaptor.getValue());
    }

    @Test
    void shouldNotFindClientByIdOnSavePayment() {
        given(payment.getClient()).willReturn(client);
        given(repository.findById(Mockito.anyInt())).willReturn(Optional.empty());

        assertThrows(NotFoundExeption.class, () -> service.savePayment(payment));
        then(repository).should().findById(payment.getClient().getId());
    }

    @Test
    void shouldGetClientById() {
        given(repository.findById(Mockito.anyInt())).willReturn(Optional.of(client));

        Client clientSearched = service.getClientById(Mockito.anyInt());

        then(repository).should().findById(Mockito.anyInt());
        assertEquals(clientSearched, client);
    }

    @Test
    void shouldNotFindClientById() {
        given(repository.findById(Mockito.anyInt())).willReturn(Optional.empty());

        assertThrows(NotFoundExeption.class, () -> service.getClientById(Mockito.anyInt()));
        then(repository).should().findById(Mockito.anyInt());
    }

    @Test
    void shouldDeleteClientById() {
        given(repository.existsById(Mockito.anyInt())).willReturn(true);

        service.deleteClient(Mockito.anyInt());

        then(repository).should().existsById(Mockito.anyInt());
        then(repository).should().deleteById(Mockito.anyInt());
    }

    @Test
    void shouldNotFindClientByIdOnDelete() {
        given(repository.existsById(Mockito.anyInt())).willReturn(false);

        assertThrows(NotFoundExeption.class, () -> service.deleteClient(Mockito.anyInt()));

        then(repository).should().existsById(Mockito.anyInt());
    }
}