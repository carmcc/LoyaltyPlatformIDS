package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Recensione;
import it.unicam.cs.ids.loyaltyplatform.service.RecensioneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recensione")
public class RecensioneController {
    final RecensioneService recensioneService;
    public RecensioneController(RecensioneService recensioneService) {this.recensioneService = recensioneService;}
    @GetMapping("/getRecensioniByIdConsumatore/{id}")
    public List<Recensione> getRecensioniByIdConsumatore(@PathVariable("id") Integer id) {return this.recensioneService.getRecensioniByIdConsumatore(id);}
    @GetMapping("/getRecensioniByIdAzienda/{id}")
    public List<Recensione> getRecensioniByIdAzienda(@PathVariable("id") Integer id) {return this.recensioneService.getRecensioniByIdAzienda(id);}
    @GetMapping("/getRecensioneByConsumatoreAndAzienda/{idConsumatore}&{idAzienda}")
    public Optional<Recensione> getRecensioneByConsumatoreAndAzienda(@PathVariable("idConsumatore") Integer idConsumatore, @PathVariable("idAzienda") Integer idAzienda) {
        return this.recensioneService.getRecensioneByConsumatoreAndAzienda(idConsumatore,idAzienda);
    }
    @GetMapping("/getAllRecensioni")
    public List<Recensione> getAllRecensioni() {return this.recensioneService.getAllRecensioni();}
    @PostMapping("/addRecensione")
    public Recensione addRecensione(@RequestBody Recensione recensione) {return this.recensioneService.AddRecensione(recensione);}
    @DeleteMapping("/deleteRecensione")
    public void deleteRecensione(@RequestBody Recensione recensione) {this.recensioneService.deleteRecensione(recensione);}
    @PutMapping("/updateRecensione")
    public void updateRecensione(@RequestBody Recensione recensione) {this.recensioneService.updateRecensione(recensione);}
}
