package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Pagamento;
import it.unicam.cs.ids.loyaltyplatform.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    public Pagamento getPagamentoById(Integer id) {return this.pagamentoRepository.findById(id).orElse(null);}
    public List<Pagamento> getAllPagamenti() {return this.pagamentoRepository.findAll();}
    public Pagamento addPagamento(Pagamento pagamento) {return this.pagamentoRepository.save(pagamento);}
    public void deletePagamentoById(Integer id) {this.pagamentoRepository.deleteById(id);}
    public void updatePagamento(Pagamento pagamento) {this.pagamentoRepository.saveAndFlush(pagamento);}
}
