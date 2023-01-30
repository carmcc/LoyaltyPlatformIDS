package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import it.unicam.cs.ids.loyaltyplatform.entity.Pagamento;
import it.unicam.cs.ids.loyaltyplatform.service.AdesioneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adesione")
@AllArgsConstructor
public class AdesioneController extends EntityValidator
{
    private final AdesioneService adesioneService;

    @GetMapping(value = "/getAdesioniByIdConsumatore/{id}")
    public List<Adesione> getAdesioniByIdConsumatore(@PathVariable("id") Integer id) {
        return this.adesioneService.getAdesioniByIdConsumatore(id);
    }
    @GetMapping(value = "/getAdesioniByIdAzienda/{id}")
    public List<Adesione> getAdesioniByIdAzienda(@PathVariable("id") Integer id) {
        return this.adesioneService.getAdesioniByIdAzienda(id);
    }
    @GetMapping(value = "/getAdesioneByConsumatoreAndAzienda/{idAzienda}&{idConsumatore}")
    public Optional<Adesione> getAdesioneByConsumatoreAndAzienda(@PathVariable("idConsumatore") Integer idConsumatore,
                                                                 @PathVariable("idAzienda") Integer idAzienda) {
        return this.adesioneService.getAdesioneByConsumatoreAndAzienda(idConsumatore, idAzienda);
    }

    @GetMapping(value = "/getAllAdesioni")
    public List<Adesione> getAllAdesioni() {
        return this.adesioneService.getAllAdesioni();
    }

    @PostMapping(value = "/addAdesione")
    public Adesione addAdesione(@RequestBody Adesione adesione) {
        validateEntity(adesione);
        if (getAdesioneByConsumatoreAndAzienda(adesione.getQualeConsumatore(),adesione.getQualeAzienda()).isPresent())
            throw new IllegalArgumentException("ID del record da aggiungere già presente");
        return this.adesioneService.addAdesione(adesione);
    }

    @DeleteMapping(value = "/deleteAdesione")
    public void deleteAdesione(@RequestBody Adesione adesione) {
        validateEntity(adesione);
        if(getAdesioneByConsumatoreAndAzienda(adesione.getQualeConsumatore(),adesione.getQualeAzienda()).isEmpty())
            throw new IllegalArgumentException("ID del record da eliminare non presente");
        this.adesioneService.deleteAdesione(adesione);
    }

    @PutMapping(value = "/updateAdesione")
    public void updateAdesione(@RequestBody Adesione adesione) {
        validateEntity(adesione);
        if (getAdesioneByConsumatoreAndAzienda(adesione.getQualeConsumatore(),adesione.getQualeAzienda()).isEmpty())
            throw new IllegalArgumentException("ID del record da modificare non presente");
        this.adesioneService.updateAdesione(adesione);
    }

    @PutMapping(value = "/incrementoPunti")
    public Adesione incrementoPunti(@RequestBody Pagamento pagamento) {
        return this.adesioneService.incrementoPunti(pagamento);
    }
}
