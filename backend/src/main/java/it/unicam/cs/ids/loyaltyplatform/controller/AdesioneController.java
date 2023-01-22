package it.unicam.cs.ids.loyaltyplatform.controller;
import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import it.unicam.cs.ids.loyaltyplatform.service.AdesioneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adesione")
public class AdesioneController
{
    private final AdesioneService adesioneService;

    public AdesioneController(AdesioneService adesioneService) {
        this.adesioneService = adesioneService;
    }

    @GetMapping(value = "/getAdesioniByIdConsumatore/{id}")
    public List<Adesione> getAdesioniByIdConsumatore(@PathVariable("id") List<Integer> id) {
        return this.adesioneService.getAdesioniByIdConsumatore(id);
    }
    @GetMapping(value = "/getAdesioneByIdConsumatoreAndByIdAzienda/{idAzienda}&{idConsumatore}")
    public Adesione getAdesioneByIdConsumatoreAndByIdAzienda(@PathVariable("idAzienda") List<Integer> idAzienda, @PathVariable("idConsumatore") Integer idConsumatore) {
        return this.adesioneService.getAdesioneByIdAzienda(getAdesioniByIdConsumatore(idAzienda), idConsumatore);
    }

    @GetMapping(value = "/getAllAdesioni")
    public List<Adesione> getAllAdesioni() {
        return this.adesioneService.getAllAdesioni();
    }

    @PostMapping(value = "/addAdesione")
    public Adesione addAdesione(Adesione adesione) {
        return this.adesioneService.addAdesione(adesione);
    }

    @DeleteMapping(value = "/deleteAdesioneById/{id}")
    public void deleteAdesioneById(@PathVariable("id") Integer id) {
        this.adesioneService.deleteAdesioneById(id);
    }

    @PutMapping(value = "/updateAdesione")
    public void updateAdesione(Adesione adesione) {
        this.adesioneService.updateAdesione(adesione);
    }
}
