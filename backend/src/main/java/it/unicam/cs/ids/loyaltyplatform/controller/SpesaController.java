package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Spesa;
import it.unicam.cs.ids.loyaltyplatform.service.SpesaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spesa")
@AllArgsConstructor
public class SpesaController {
    private final SpesaService spesaService;
    @GetMapping("/getSpeseByIdPagamento/{id}")
    public List<Spesa> getSpeseByIdPagamento(@PathVariable("id") Integer id) {
        return this.spesaService.getSpeseByIdPagamento(id);
    }
    @GetMapping("/getSpeseByIdProdotto/{id}")
    public List<Spesa> getSpeseByIdProdotto(@PathVariable("id") Integer id) {
        return this.spesaService.getSpeseByIdProdotto(id);
    }
    @GetMapping("/getSpesaByPagamentoAndProdotto/{idPagamento}&{idProdotto}")
    public Optional<Spesa> getSpesaByPagamentoAndProdotto(@PathVariable("idPagamento") Integer idPagamento,
                                                          @PathVariable("idProdotto") Integer idProdotto) {
        return this.spesaService.getSpesaByPagamentoAndProdotto(idPagamento, idProdotto);
    }
    @GetMapping("/getAllSpese")
    public List<Spesa> getAllSpese() {return this.spesaService.getAllSpese();}
    @PostMapping("/addSpesa")
    public Spesa addSpesa(@RequestBody Spesa spesa) {
        controlloValiditaSpesa(spesa);
        if(getSpesaByPagamentoAndProdotto(spesa.getQualePagamento(),spesa.getQualeProdotto()).isPresent())
            throw new IllegalArgumentException("il record da aggiungere esiste già");

        return this.spesaService.addSpesa(spesa);
    }
    @DeleteMapping("/deleteSpesa")
    public void deleteSpesa(@RequestBody Spesa spesa) {
        controlloValiditaSpesa(spesa);
        if(getSpesaByPagamentoAndProdotto(spesa.getQualePagamento(),spesa.getQualeProdotto()).isEmpty())
            throw new IllegalArgumentException("il record da rimuovere non esiste");

        this.spesaService.deleteSpesa(spesa);
    }
    @PutMapping("/updateSpesa")
    public void updateSpesa(@RequestBody Spesa spesa) {
        controlloValiditaSpesa(spesa);
        if(getSpesaByPagamentoAndProdotto(spesa.getQualePagamento(),spesa.getQualeProdotto()).isEmpty())
            throw new IllegalArgumentException("il record da aggiornare non esiste");

        this.spesaService.updateSpesa(spesa);
    }

    private void controlloValiditaSpesa(Spesa spesa) {
        if(spesa == null) throw new NullPointerException("spesa è nulla");
        if(spesa.getQuantita() == null || spesa.getQuantita() < 1)
            throw new IllegalArgumentException("quantità spesa non valida");
    }
}
