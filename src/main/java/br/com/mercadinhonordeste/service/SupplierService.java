package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Supplier;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository repository;

    public ApiResponse<Supplier> saveSupplier(Supplier supplier) {
        ApiResponse<Supplier> response = new ApiResponse<>();
        return response.of(HttpStatus.CREATED, "Fornecedor cadastrado com sucesso", repository.save(supplier));
    }

    public ApiResponse<List<Supplier>> listSuppliers(String name) {
        ApiResponse<List<Supplier>> response = new ApiResponse<>();
        List<Supplier> suppliers = repository.findByNameContainingIgnoreCase(name, Sort.by(Sort.Direction.ASC, "name"));
        return response.of(HttpStatus.OK, "Fornecedores encontrados", suppliers);
    }
}
