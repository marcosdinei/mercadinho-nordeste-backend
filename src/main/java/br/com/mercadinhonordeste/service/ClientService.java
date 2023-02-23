package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Client;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.model.Pagination;
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

    public ApiResponse<Client> saveClient(Client client) {
        ApiResponse<Client> response = new ApiResponse<>();
        return response.of(HttpStatus.CREATED, "Cliente cadastrado com sucesso", repository.save(client));
    }

    public ApiResponse<Client> updateClient(Client client) {
        ApiResponse<Client> response = new ApiResponse<>();
        if (!repository.existsById(client.getId()))
            return response.of(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        return response.of(HttpStatus.OK, "Cliente atualizado com sucesso", repository.save(client));
    }

    public ApiResponse<PaginatedData<Client>> listClients(String name, Pageable pageable) {
        ApiResponse<PaginatedData<Client>> response = new ApiResponse<>();
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name")
        );
        Page<Client> clients = repository.findByNameContainingIgnoreCase(name, pageable);
        final Pagination pagination = Pagination.from(clients, pageable);
        return response.of(
                HttpStatus.OK,
                pagination.getTotalNumberOfElements().toString().concat(" cliente(s) encontrado(s)"),
                new PaginatedData<>(clients.getContent(), pagination));
    }

    public ApiResponse<Client> getClientById(Integer id) {
        ApiResponse<Client> response = new ApiResponse<>();
        Optional<Client> client = repository.findById(id);
        if (client.isEmpty())
            return response.of(HttpStatus.NOT_FOUND, "Cliente não encontrado com o id informado");
        return response.of(HttpStatus.OK, "Cliente encontrado com sucesso", client.get());
    }

    public ApiResponse<?> deleteClient(Integer id) {
        ApiResponse<?> response = new ApiResponse<>();
        if (!repository.existsById(id))
            return response.of(HttpStatus.NOT_FOUND, "Cliente não encontrado com o id informado");
        repository.deleteById(id);
        return response.of(HttpStatus.OK, "Cliente deletado com sucesso");
    }
}
