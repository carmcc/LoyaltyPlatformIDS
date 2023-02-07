package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.entity.BuonoSconto;
import it.unicam.cs.ids.loyaltyplatform.entity.Pagamento;
import it.unicam.cs.ids.loyaltyplatform.service.AdesioneService;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.service.BuonoScontoService;
import it.unicam.cs.ids.loyaltyplatform.service.PagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
@AllArgsConstructor
public class PagamentoController extends EntityValidator{
    private final PagamentoService pagamentoService;
    private final AdesioneService adesioneService;
    private final AziendaService aziendaService;
    private final BuonoScontoService buonoScontoService;

    @GetMapping("/getPagamentoById/{id}")
    public Pagamento getPagamentoById(@PathVariable("id") Integer id) {return this.pagamentoService.getPagamentoById(id);}
    @GetMapping("/getAllPagamenti")
    public List<Pagamento> getAllPagamenti() {return this.pagamentoService.getAllPagamenti();}
    @PostMapping("/addPagamento")
    public Pagamento addPagamento(@RequestBody Pagamento pagamento) {
        validateEntity(pagamento);
        if(pagamento.getIdPagamento() != null && getPagamentoById(pagamento.getIdPagamento()) != null)
            throw new IllegalArgumentException("il record da aggiungere esiste già");

        Integer idAzienda = pagamento.getQualeAzienda();
        Integer idConsumatore = pagamento.getQualeConsumatore();
        if(this.adesioneService.getAdesioneByConsumatoreAndAzienda(idConsumatore, idAzienda).isPresent())   //se il consumatore ha un'adesione con l'azienda, allora si applicano i vantaggi
        {
            Azienda azienda = this.aziendaService.getAziendaById(idAzienda);
            if (azienda.getQualeCoalizione() != null)   //se esiste una coalizione allora creo un buono sconto
            {
                Float spesa = pagamento.getCostoTotale();
                BuonoSconto buonoSconto = this.buonoScontoService.createNewBuonoSconto(idConsumatore, azienda, spesa);
                this.buonoScontoService.addBuonoSconto(buonoSconto);
            }

            //TODO implementare il cashback
        }
        return this.pagamentoService.addPagamento(pagamento);   //Ma che cazzo!? Invocare "pagamentoService.addPagamento(pagamento)" casusa un errore in "buonoScontoService.addBuonoSconto(buonoSconto)"
    }
    @DeleteMapping("/deletePagamentoById/{id}")
    public void deletePagamentoById(@PathVariable("id") Integer id) {
        if(getPagamentoById(id) == null)
            throw new IllegalArgumentException("il record da rimuovere non esiste");

        this.pagamentoService.deletePagamentoById(id);
    }
    @PutMapping("/updatePagamento")
    public void updatePagamento(@RequestBody Pagamento pagamento) {
        validateEntity(pagamento);
        if(pagamento.getIdPagamento() == null)
            throw new NullPointerException("id pagamento non deve essere nullo per l'update");
        if(getPagamentoById(pagamento.getIdPagamento()) == null)
            throw new IllegalArgumentException("il record da aggiornare non esiste");

        this.pagamentoService.updatePagamento(pagamento);
    }
}
