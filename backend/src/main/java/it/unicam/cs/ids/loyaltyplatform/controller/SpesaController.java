package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Spesa;
import it.unicam.cs.ids.loyaltyplatform.service.SpesaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spesa")
public class SpesaController {
    private final SpesaService spesaService;
    public SpesaController(SpesaService spesaService) {this.spesaService = spesaService;}
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
    public Spesa addSpesa(@RequestBody Spesa spesa) {return this.spesaService.AddSpesa(spesa);}
    @DeleteMapping("/deleteSpesa")
    public void deleteSpesa(@RequestBody Spesa spesa) {this.spesaService.deleteSpesa(spesa);}
    @PutMapping("/updateSpesa")
    public void updateSpesa(@RequestBody Spesa spesa) {this.spesaService.updateSpesa(spesa);}
}
