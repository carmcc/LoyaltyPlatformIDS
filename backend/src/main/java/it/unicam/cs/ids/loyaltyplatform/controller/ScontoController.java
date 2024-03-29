package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Sconto;
import it.unicam.cs.ids.loyaltyplatform.service.ScontoService;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityValidator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sconto")
@AllArgsConstructor
public class ScontoController extends EntityValidator
{
    private final ScontoService scontoService;
    @GetMapping("/getScontiByIdAzienda/{id}")
    public List<Sconto> getScontiByIdAzienda(@PathVariable("id") Integer id) {
        return this.scontoService.getScontiByIdAzienda(id);
    }
    @GetMapping("/getScontiByIdProdotto/{id}")
    public List<Sconto> getScontiByIdProdotto(@PathVariable("id") Integer id) {
        return this.scontoService.getScontiByIdProdotto(id);
    }
    @GetMapping("/getScontoByAziendaAndProdotto/{idAzienda}&{idProdotto}")
    public Optional<Sconto> getScontoByAziendaAndProdotto(@PathVariable("idAzienda") Integer idAzienda,
                                                          @PathVariable("idProdotto") Integer idProdotto) {
        return this.scontoService.getScontoByAziendaAndProdotto(idAzienda, idProdotto);
    }

    @GetMapping("/getAllSconti")
    public List<Sconto> getAllSconti() {
        return this.scontoService.getAllSconti();
    }

    @PostMapping("/addSconto")
    public Sconto addSconto(@RequestBody Sconto sconto) {
        validateEntity(sconto);
        return this.scontoService.addSconto(sconto);
    }

    @DeleteMapping("/deleteSconto")
    public void deleteSconto(@RequestBody Sconto sconto) {
        validateEntity(sconto);
        if(getScontoByAziendaAndProdotto(sconto.getQualeAzienda(), sconto.getQualeProdotto()).isEmpty())
            throw new IllegalArgumentException("ID non validi per la cancellazione dello sconto");
        this.scontoService.deleteSconto(sconto);
    }

    @PutMapping("/updateSconto")
    public void updateSconto(@RequestBody Sconto sconto) {
        validateEntity(sconto);
        if (getScontoByAziendaAndProdotto(sconto.getQualeAzienda(), sconto.getQualeProdotto()).isEmpty())
                    throw new IllegalArgumentException("Sconto non presente");
        this.scontoService.updateSconto(sconto);
    }
}
