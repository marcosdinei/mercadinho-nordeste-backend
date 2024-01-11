package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Client;
import br.com.mercadinhonordeste.entity.ClientPayment;
import br.com.mercadinhonordeste.exception.NotFoundExeption;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.model.Pagination;
import br.com.mercadinhonordeste.repository.ClientPaymentRepository;
import br.com.mercadinhonordeste.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final ClientPaymentRepository paymentRepository;

    public Client saveClient(Client client) {
        return repository.save(client);
    }

    public Client updateClient(Client client) {
        if (!repository.existsById(client.getId()))
            throw new NotFoundExeption("Cliente não encontrado");
        return repository.save(client);
    }

    public ClientPayment savePayment(ClientPayment payment) {
        Optional<Client> client = repository.findById(payment.getClient().getId());
        if (client.isEmpty())
            throw new NotFoundExeption("Cliente não encontrado com o id informado");

        Client clientToSave = client.get();
        clientToSave.setAmountToPay(clientToSave.getAmountToPay() - payment.getAmountPaid());
        payment.setClient(repository.save(clientToSave));

        return paymentRepository.save(payment);
    }

    public PaginatedData<Client> listClients(String name, Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name")
        );
        Page<Client> clients = repository.findByNameContainingIgnoreCase(name, pageable);
        final Pagination pagination = Pagination.from(clients, pageable);
        return new PaginatedData<>(clients.getContent(), pagination);
    }

    public Client getClientById(Integer id) {
        Optional<Client> client = repository.findById(id);
        if (client.isEmpty())
            throw new NotFoundExeption("Cliente não encontrado com o id informado");
        return client.get();
    }

    public void deleteClient(Integer id) {
        if (!repository.existsById(id))
            throw new NotFoundExeption("Cliente não encontrado com o id informado");
        repository.deleteById(id);
    }
}
