package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.entity.BuonoSconto;
import it.unicam.cs.ids.loyaltyplatform.entity.Pagamento;
import it.unicam.cs.ids.loyaltyplatform.service.AdesioneService;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.service.BuonoScontoService;
import it.unicam.cs.ids.loyaltyplatform.service.PagamentoService;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityValidator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/pagamento")
@AllArgsConstructor
public class PagamentoController extends EntityValidator {
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
        pagamento.setIdPagamento(null);

        Integer idAzienda = pagamento.getQualeAzienda();
        Integer idConsumatore = pagamento.getQualeConsumatore();
        Optional<Adesione> adesioneOptional = this.adesioneService.getAdesioneByConsumatoreAndAzienda(idConsumatore, idAzienda);
        if(adesioneOptional.isPresent())   //se il consumatore ha un'adesione con l'azienda, allora si applicano i vantaggi
        {
            Adesione adesione = adesioneOptional.get();
            Azienda azienda = this.aziendaService.getAziendaById(idAzienda);
            Float spesa = pagamento.getCostoTotale();

            //aggiungo un buono sconto
            if (azienda.getQualeCoalizione() != null)   //se esiste una coalizione allora creo un buono sconto
            {
                BuonoSconto buonoSconto = this.buonoScontoService.createNewBuonoSconto(idConsumatore, azienda, spesa);
                this.buonoScontoService.addBuonoSconto(buonoSconto);
            }

            //aggiornamento data "ultimaSpesa"
            adesione.setUltimaSpesa(Calendar.getInstance(Locale.ITALY).getTime());

            //incremento il salvadanaio (cashBack)
            float incrementoCashBack = spesa / azienda.getDivisoreCashback();
            incrementoCashBack = new BigDecimal(incrementoCashBack)
                                            .setScale(2, RoundingMode.HALF_UP).floatValue();
            adesione.setSalvadanaio(adesione.getSalvadanaio() + incrementoCashBack);
            this.adesioneService.updateAdesione(adesione);

            //incremento punti ed esperienza
            this.adesioneService.incrementoPuntiEdEsperienza(pagamento);
        }
        return this.pagamentoService.addPagamento(pagamento);
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
