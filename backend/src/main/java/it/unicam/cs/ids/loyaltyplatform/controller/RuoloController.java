package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Ruolo;
import it.unicam.cs.ids.loyaltyplatform.service.RuoloService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ruolo")
@AllArgsConstructor
public class RuoloController implements ValidateEntity{
    private final RuoloService ruoloService;
    @GetMapping("/getRuoliByIdAzienda/{id}")
    public List<Ruolo> getRuoliByIdAzienda(@PathVariable("id") Integer id) {
        return this.ruoloService.getRuoliByIdAzienda(id);
    }
    @GetMapping("/getRuoliByAziendaAndPermesso/{id}&{permesso}")
    public List<Ruolo> getRuoliByAziendaAndPermesso(@PathVariable("id") Integer id, @PathVariable("permesso") String permesso) {
        return this.ruoloService.getRuoliByAziendaAndPermesso(id, permesso);
    }
    @GetMapping("/getRuoliByAziendaAndSeriale/{id}&{seriale}")
    public List<Ruolo> getRuoliByAziendaAndSeriale(@PathVariable("id") Integer id, @PathVariable("seriale") Integer seriale) {
        return this.ruoloService.getRuoliByAziendaAndSeriale(id, seriale);
    }
    @GetMapping("/getRuoloByAziendaSerialeAndPermesso/{idAzienda}&{seriale}&{nomePermesso}")
    public Optional<Ruolo> getRuoloByAziendaSerialeAndPermesso
            (@PathVariable("idAzienda")Integer idAzienda, @PathVariable("seriale")Integer seriale, @PathVariable("nomePermesso")String nomePermesso) {
        return this.ruoloService.getRuoloByAziendaSerialeAndPermesso(idAzienda, seriale, nomePermesso);
    }
    @GetMapping("/getAllRuoli")
    public List<Ruolo> getAllRuoli() {return this.ruoloService.getAllRuoli();}
    @PostMapping("/addRuolo")
    public Ruolo addRuolo(@RequestBody Ruolo ruolo) {
        validateEntity(ruolo);
        if(getRuoloByAziendaSerialeAndPermesso(ruolo.getQualeAccountAziendale(),ruolo.getQualeSeriale(),ruolo.getQualePermesso()).isPresent())
            throw new IllegalArgumentException("il record da aggiungere esiste già");
        return this.ruoloService.addRuolo(ruolo);
    }
    @DeleteMapping("/deleteRuolo")
    public void deleteRuolo(@RequestBody Ruolo ruolo) {
        validateEntity(ruolo);
        if(getRuoloByAziendaSerialeAndPermesso(ruolo.getQualeAccountAziendale(),ruolo.getQualeSeriale(),ruolo.getQualePermesso()).isEmpty())
            throw new IllegalArgumentException("il record da rimuovere non esiste");
        this.ruoloService.deleteRuolo(ruolo);
    }
    @PutMapping("/updateRuolo")
    public void updateRuolo(@RequestBody Ruolo ruolo) {
        validateEntity(ruolo);
        if(getRuoloByAziendaSerialeAndPermesso(ruolo.getQualeAccountAziendale(),ruolo.getQualeSeriale(),ruolo.getQualePermesso()).isEmpty())
            throw new IllegalArgumentException("il record da aggiornare non esiste");
        this.ruoloService.updateRuolo(ruolo);
    }

    @Override
    public void validateEntity(Object entity) {
        Ruolo ruolo = (Ruolo) entity;
        if(ruolo == null)
            throw new NullPointerException("ruolo è nullo");
        if(ruolo.getNome().length() < 2)
            throw new IllegalArgumentException("nome non valido");
        if(ruolo.getQualeAccountAziendale() <= 0 || ruolo.getQualeSeriale() <= 0 || ruolo.getQualePermesso().isBlank())
            throw new IllegalArgumentException("Parametri non validi");
    }
}
