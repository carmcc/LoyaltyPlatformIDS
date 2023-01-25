package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Pagamento;
import it.unicam.cs.ids.loyaltyplatform.service.PagamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    private final PagamentoService pagamentoService;
    public PagamentoController(PagamentoService pagamentoService) {this.pagamentoService = pagamentoService;}
    @GetMapping("/getPagamentoById/{id}")
    public Pagamento getPagamentoById(@PathVariable("id") Integer id) {return this.pagamentoService.getPagamentoById(id);}
    @GetMapping("/getAllPagamenti")
    public List<Pagamento> getAllPagamenti() {return this.pagamentoService.getAllPagamenti();}
    @PostMapping("/addPagamento")
    public Pagamento addPagamento(@RequestBody Pagamento pagamento) {return this.pagamentoService.addPagamento(pagamento);}
    @DeleteMapping("/deletePagamentoById/{id}")
    public void deletePagamentoById(@PathVariable("id") Integer id) {this.pagamentoService.removePagamentoById(id);}
    @PutMapping("/updatePagamento")
    public void updatePagamento(@RequestBody Pagamento pagamento) {this.pagamentoService.updatePagamento(pagamento);}
}
