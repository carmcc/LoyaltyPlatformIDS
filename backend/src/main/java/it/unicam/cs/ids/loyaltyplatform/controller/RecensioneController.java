package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Recensione;
import it.unicam.cs.ids.loyaltyplatform.service.RecensioneService;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityValidator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recensione")
@AllArgsConstructor
public class RecensioneController extends EntityValidator {
    final RecensioneService recensioneService;
    @GetMapping("/getRecensioniByIdConsumatore/{id}")
    public List<Recensione> getRecensioniByIdConsumatore(@PathVariable("id") Integer id) {
        return this.recensioneService.getRecensioniByIdConsumatore(id);
    }
    @GetMapping("/getRecensioniByIdAzienda/{id}")
    public List<Recensione> getRecensioniByIdAzienda(@PathVariable("id") Integer id) {
        return this.recensioneService.getRecensioniByIdAzienda(id);
    }
    @GetMapping("/getRecensioneByConsumatoreAndAzienda/{idConsumatore}&{idAzienda}")
    public Optional<Recensione> getRecensioneByConsumatoreAndAzienda(@PathVariable("idConsumatore") Integer idConsumatore,
                                                                     @PathVariable("idAzienda") Integer idAzienda) {
        return this.recensioneService.getRecensioneByConsumatoreAndAzienda(idConsumatore,idAzienda);
    }
    @GetMapping("/getAllRecensioni")
    public List<Recensione> getAllRecensioni() {return this.recensioneService.getAllRecensioni();}
    @PostMapping("/addRecensione")
    public Recensione addRecensione(@RequestBody Recensione recensione) {
        validateEntity(recensione);
        if(getRecensioneByConsumatoreAndAzienda(recensione.getQualeConsumatore(),recensione.getQualeAzienda()).isPresent())
            throw new IllegalArgumentException("il record da aggiungere esiste già");
        return this.recensioneService.AddRecensione(recensione);
    }
    @DeleteMapping("/deleteRecensione")
    public void deleteRecensione(@RequestBody Recensione recensione) {
        validateEntity(recensione);
        if(getRecensioneByConsumatoreAndAzienda(recensione.getQualeConsumatore(),recensione.getQualeAzienda()).isEmpty())
            throw new IllegalArgumentException("il record da rimuovere non esiste");

        this.recensioneService.deleteRecensione(recensione);
    }
    @PutMapping("/updateRecensione")
    public void updateRecensione(@RequestBody Recensione recensione) {
        validateEntity(recensione);
        if(getRecensioneByConsumatoreAndAzienda(recensione.getQualeConsumatore(),recensione.getQualeAzienda()).isEmpty())
            throw new IllegalArgumentException("il record da aggiornare non esiste");

        this.recensioneService.updateRecensione(recensione);
    }

    @PutMapping("/writeRewiew")
    public Recensione writeRewiew(@RequestBody Recensione recensione) {
        validateEntity(recensione);
        if(getRecensioneByConsumatoreAndAzienda(recensione.getQualeConsumatore(),recensione.getQualeAzienda()).isEmpty())
            addRecensione(this.recensioneService.writeRewiew(recensione));
        else
            updateRecensione(this.recensioneService.writeRewiew(recensione));
        return this.recensioneService.writeRewiew(recensione);
    }

}
