package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Premio;
import it.unicam.cs.ids.loyaltyplatform.service.PremioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/premio")
@AllArgsConstructor
public class PremioController extends EntityValidator{
    private final PremioService premioService;
    @GetMapping("/getPremiByIdAzienda/{id}")
    public List<Premio> getPremiByIdAzienda(@PathVariable("id") Integer id) {return this.premioService.getPremiByIdAzienda(id);}
    @GetMapping("/getPremiByIdProdotto/{id}")
    public List<Premio> getPremiByIdProdotto(@PathVariable("id") Integer id) {return this.premioService.getPremiByIdProdotto(id);}
    @GetMapping("/getPremioByAziendaAndProdotto/{idAzienda}&{idProdotto}")
    public Optional<Premio> getPremioByAziendaAndProdotto(@PathVariable("idAzienda") Integer idAzienda,
                                                          @PathVariable("idProdotto") Integer idProdotto) {
        return this.premioService.getPremioByAziendaAndProdotto(idAzienda,idProdotto);
    }
    @GetMapping("/getAllPremi")
    public List<Premio> getAllPremi() {return this.premioService.getAllPremi();}
    @PostMapping("/addPremio")
    public Premio addPremio(@RequestBody Premio premio) {
        validateEntity(premio);
        if(getPremioByAziendaAndProdotto(premio.getQualeAzienda(),premio.getQualeProdotto()).isPresent())
            throw new IllegalArgumentException("il record da aggiungere esiste già");
        return this.premioService.addPremio(premio);
    }
    @DeleteMapping("/deletePremio")
    public void deletePremio(@RequestBody Premio premio) {
        validateEntity(premio);
        if(getPremioByAziendaAndProdotto(premio.getQualeAzienda(),premio.getQualeProdotto()).isEmpty())
            throw new IllegalArgumentException("il record da rimuovere non esiste");
        this.premioService.deletePremio(premio);
    }
    @PutMapping("/updatePremio")
    public void updatePremio(@RequestBody Premio premio) {
        validateEntity(premio);
        if(getPremioByAziendaAndProdotto(premio.getQualeAzienda(),premio.getQualeProdotto()).isEmpty())
            throw new IllegalArgumentException("il record da aggiornare non esiste");
        this.premioService.updatePremio(premio);
    }
}
