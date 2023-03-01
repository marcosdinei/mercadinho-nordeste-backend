package br.com.mercadinhonordeste.controller;

import br.com.mercadinhonordeste.entity.Client;
import br.com.mercadinhonordeste.entity.ClientPayment;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("client")
public class ClientController {
    private final ClientService service;

    @PostMapping()
    public ResponseEntity<ApiResponse<Client>> saveClient(@RequestBody Client client) {
        ApiResponse<Client> response = service.saveClient(client);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<Client>> updateClient(@RequestBody Client client) {
        ApiResponse<Client> response = service.updateClient(client);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/payment")
    public ResponseEntity<ApiResponse<ClientPayment>> savePayment(@RequestBody ClientPayment payment) {
        ApiResponse<ClientPayment> response = service.savePayment(payment);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<PaginatedData<Client>>> listClients(
            String name, @PageableDefault() Pageable pageable) {
        ApiResponse<PaginatedData<Client>> response = service.listClients(name, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> getClientById(@PathVariable Integer id) {
        ApiResponse<Client> response = service.getClientById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping()
    public ResponseEntity<ApiResponse<?>> deleteClient(Integer id) {
        ApiResponse<?> response = service.deleteClient(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
