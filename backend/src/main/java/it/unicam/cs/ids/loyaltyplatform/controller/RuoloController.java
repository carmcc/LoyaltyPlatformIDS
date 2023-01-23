package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Ruolo;
import it.unicam.cs.ids.loyaltyplatform.service.RuoloService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ruolo")
public class RuoloController {
    private final RuoloService ruoloService;
    public RuoloController(RuoloService ruoloService) {this.ruoloService = ruoloService;}
    @GetMapping("/getRuoliByIdAzienda/{id}")
    public List<Ruolo> getRuoliByIdAzienda(@PathVariable("id") Integer id) {
        return this.ruoloService.getRuoliByIdAzienda(id);
    }
    @GetMapping("/getRuoliByAziendaAndPermesso/{id}&{permesso}")
    public List<Ruolo> getRuoloByAziendaAndPermesso(@PathVariable("id") Integer id, @PathVariable("permesso") String permesso) {
        return this.ruoloService.getRuoliByAziendaAndPermesso(id, permesso);
    }
    @GetMapping("/getRuoloByAziendaAndSeriale/{id}&{seriale}")
    public Optional<Ruolo> getRuoloByAziendaAndSeriale(@PathVariable("id") Integer id, @PathVariable("seriale") Integer seriale) {
        return this.ruoloService.getRuoliByAziendaAndSeriale(id, seriale);
    }
    @GetMapping("/getAllRuoli")
    public List<Ruolo> getAllRuoli() {return this.ruoloService.getAllRuoli();}
    @PostMapping("/AddRecensione")
    public Ruolo AddRecensione(@RequestBody Ruolo ruolo) {return this.ruoloService.AddRecensione(ruolo);}
    @DeleteMapping("/deleteRecensione")
    public void deleteRecensione(@RequestBody Ruolo ruolo) {this.ruoloService.deleteRecensione(ruolo);}
    @PutMapping("/updateRecensione")
    public void updateRecensione(@RequestBody Ruolo ruolo) {this.ruoloService.updateRecensione(ruolo);}
}
