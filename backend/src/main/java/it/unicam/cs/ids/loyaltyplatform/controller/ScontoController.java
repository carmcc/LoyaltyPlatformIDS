package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Sconto;
import it.unicam.cs.ids.loyaltyplatform.service.ScontoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sconto")
public class ScontoController {
    private final ScontoService scontoService;
    public ScontoController(ScontoService scontoService) {this.scontoService = scontoService;}
    @GetMapping("/getScontiByIdAzienda/{id}")
    public List<Sconto> getScontiByIdAzienda(@PathVariable("id") Integer id) {
        return this.scontoService.getScontiByIdAzienda(id);
    }
    @GetMapping("/getScontiByIdProdotto/{id}")
    public List<Sconto> getScontiByIdProdotto(@PathVariable("id") Integer id) {
        return this.scontoService.getScontiByIdProdotto(id);
    }
    @GetMapping("/getScontoByAziendaAndProdotto/{idAzienda}&{idProdotto}")
    public Optional<Sconto> getScontoByAziendaAndProdotto(@PathVariable("idAzienda") Integer idAzienda,@PathVariable("idProdotto") Integer idProdotto) {
        return this.scontoService.getScontoByAziendaAndProdotto(idAzienda, idProdotto);
    }
    @GetMapping("/getAllSconti")
    public List<Sconto> getAllSconti() {return this.scontoService.getAllSconti();}
    @PostMapping("/addSconto")
    public Sconto addSconto(@RequestBody Sconto sconto) {return this.scontoService.AddSconto(sconto);}
    @DeleteMapping("/deleteSconto")
    public void deleteSconto(@RequestBody Sconto sconto) {this.scontoService.deleteSconto(sconto);}
    @PutMapping("/updateSconto")
    public void updateSconto(@RequestBody Sconto sconto) {this.scontoService.updateSconto(sconto);}
}
