package br.com.mercadinhonordeste.service;

import br.com.mercadinhonordeste.entity.Cash;
import br.com.mercadinhonordeste.entity.Client;
import br.com.mercadinhonordeste.entity.Sale;
import br.com.mercadinhonordeste.entity.Stock;
import br.com.mercadinhonordeste.model.ApiResponse;
import br.com.mercadinhonordeste.model.PaginatedData;
import br.com.mercadinhonordeste.model.Pagination;
import br.com.mercadinhonordeste.repository.CashRepository;
import br.com.mercadinhonordeste.repository.ClientRepository;
import br.com.mercadinhonordeste.repository.SaleRepository;
import br.com.mercadinhonordeste.repository.StockRepository;
import br.com.mercadinhonordeste.service.criteria.SaleCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository repository;
    private final CashRepository cashRepository;
    private final ClientRepository clientRepository;
    private final StockRepository stockRepository;

    public ApiResponse<Sale> saveSale(Sale sale) {
        ApiResponse<Sale> response = new ApiResponse<>();

        if (sale.getPayment().getId().equals(4)){
            Cash activeCash = cashRepository.findByInProgress(true).get(0);
            activeCash.setCurrentValue(activeCash.getCurrentValue() + sale.getValuePaid());
            cashRepository.save(activeCash);
        } else if (sale.getPayment().getId().equals(5)) {
            Client client = clientRepository.findById(sale.getClient().getId()).get();
            client.setAmountToPay(client.getAmountToPay() + sale.getValuePaid());
            clientRepository.save(client);
        }

        Sale savedSale = repository.save(sale);
        savedSale.getItems().forEach(item -> {
            Stock stock = stockRepository.findByProduct(item.getProduct()).get();
            if (item.getIsBox())
                stock.setQuantity(stock.getQuantity() - (item.getQuantityItem() * item.getProduct().getBox().getQuantityProduct()));
            else
                stock.setQuantity(stock.getQuantity() - item.getQuantityItem());
        });

        return response.of(HttpStatus.CREATED, "Venda cadastrada com sucesso", savedSale);
    }

    public ApiResponse<?> deleteSale(Integer id) {
        ApiResponse<?> response = new ApiResponse<>();
        Optional<Sale> sale = repository.findById(id);

        if (sale.isEmpty())
            return response.of(HttpStatus.NOT_FOUND, "Venda não encontrada com o id informado");

        if (sale.get().getPayment().getId().equals(4)) {
            Cash activeCash = cashRepository.findByInProgress(true).get(0);
            activeCash.setCurrentValue(activeCash.getCurrentValue() - sale.get().getValuePaid());
            cashRepository.save(activeCash);
        } else if (sale.get().getPayment().getId().equals(5)) {
            Client client = clientRepository.findById(sale.get().getClient().getId()).get();
            client.setAmountToPay(client.getAmountToPay() - sale.get().getValuePaid());
            clientRepository.save(client);
        }

        sale.get().getItems().forEach(item -> {
            Stock stock = stockRepository.findByProduct(item.getProduct()).get();
            if (item.getIsBox())
                stock.setQuantity(stock.getQuantity() + (item.getQuantityItem() * item.getProduct().getBox().getQuantityProduct()));
            else
                stock.setQuantity(stock.getQuantity() + item.getQuantityItem());
            stockRepository.save(stock);
        });

        repository.deleteById(id);
        return response.of(HttpStatus.OK, "Venda deletada com sucesso");
    }

    public ApiResponse<PaginatedData<Sale>> listSales(SaleCriteria criteria, Pageable pageable) {
        ApiResponse<PaginatedData<Sale>> response = new ApiResponse<>();
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "date")
        );
        Page<Sale> sales = repository.findAll(createSpecification(criteria), pageable);
        final Pagination pagination = Pagination.from(sales, pageable);
        return response.of(
                HttpStatus.OK,
                pagination.getTotalNumberOfElements().toString().concat(" venda(s) encontrada(s)"),
                new PaginatedData<>(sales.getContent(), pagination));
    }

    private Specification<Sale> createSpecification(SaleCriteria criteria) {
        Specification<Sale> specification = Specification.where(null);

        if (criteria.getInitialDate() != null)
            specification = specification.and(SaleCriteria.filterByInitialDate(criteria.getInitialDate()));

        if (criteria.getFinalDate() != null)
            specification = specification.and(SaleCriteria.filterByFinalDate(criteria.getFinalDate()));

        if (criteria.getMinValue() != null)
            specification = specification.and(SaleCriteria.filterByMinValue(criteria.getMinValue()));

        if (criteria.getMaxValue() != null)
            specification = specification.and(SaleCriteria.filterByMaxValue(criteria.getMaxValue()));

        if (criteria.getPayment() != null)
            specification = specification.and(SaleCriteria.filterByPaymentId(criteria.getPayment()));

        if (criteria.getClient() != null)
            specification = specification.and(SaleCriteria.filterByClientId(criteria.getClient()));

        return specification;
    }
}
