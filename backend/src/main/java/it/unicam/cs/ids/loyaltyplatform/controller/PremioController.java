package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Premio;
import it.unicam.cs.ids.loyaltyplatform.service.PremioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/premio")
public class PremioController {
    private final PremioService premioService;
    public PremioController(PremioService premioService) {this.premioService = premioService;}
    @GetMapping("/getPremiByIdAzienda/{id}")
    public List<Premio> getPremiByIdAzienda(@PathVariable("id") Integer id) {return this.premioService.getPremiByIdAzienda(id);}
    @GetMapping("/getPremiByIdProdotto/{id}")
    public List<Premio> getPremiByIdProdotto(@PathVariable("id") Integer id) {return this.premioService.getPremiByIdProdotto(id);}
    @GetMapping("/getPremioByAziendaAndProdotto/{idAzienda}&{idProdotto}")
    public Optional<Premio> getPremioByAziendaAndProdotto(@PathVariable("idAzienda") Integer idAzienda, @PathVariable("idProdotto") Integer idProdotto) {
        return this.premioService.getPremioByAziendaAndProdotto(idAzienda,idProdotto);
    }
    @GetMapping("/getAllPremi")
    public List<Premio> getAllPremi() {return this.premioService.getAllPremi();}
    @PostMapping("/addPremio")
    public Premio addPremio(@RequestBody Premio premio) {return this.premioService.addPremio(premio);}
    @DeleteMapping("/deletePremio")
    public void deletePremio(@RequestBody Premio premio) {this.premioService.deletePremio(premio);}
    @PutMapping("/updatePremio")
    public void updatePremio(@RequestBody Premio premio) {this.premioService.updatePremio(premio);}
}
