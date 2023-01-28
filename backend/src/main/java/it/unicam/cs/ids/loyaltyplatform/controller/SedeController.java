package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Sede;
import it.unicam.cs.ids.loyaltyplatform.service.SedeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sede")
@AllArgsConstructor
public class SedeController implements ValidateEntity
{
    private final SedeService sedeService;
    @GetMapping(value = "/getSedeById/{id}")
    public Sede getSedeById(@PathVariable("id") Integer id) {
        return this.sedeService.getSedeById(id);
    }
    @GetMapping(value = "/getAllSedi")
    public List<Sede> getAllSedi() {
        return this.sedeService.getAllSedi();
    }

    @PostMapping(value = "/addSede")
    public Sede addSede(@RequestBody Sede sede) {
        validateEntity(sede);
        if (getSedeById(sede.getIdSede()) != null)
            throw new IllegalArgumentException("La sede inserita è già presente nel database");
        return this.sedeService.addSede(sede);
    }

    @DeleteMapping(value = "/deleteSedeById/{id}")
    public void deleteSedeById(@PathVariable("id") Integer id) {
        if((getSedeById(id)) == null)
            throw new NullPointerException("ID non valido per la cancellazione della sede");
        this.sedeService.deleteSedeById(id);
    }

    @PutMapping(value = "/updateSede")
    public void updateSede(@RequestBody Sede sede) {
        validateEntity(sede);
        if (getSedeById(sede.getIdSede()) == null)
            throw new IllegalArgumentException("Sede non presente");
        this.sedeService.updateSede(sede);
    }

    @Override
    public void validateEntity(Object entity) {
        Sede sede = (Sede) entity;
        if(sede == null)
            throw new NullPointerException("La sede inserita è nulla");
        if (sede.getIdSede() <= 0 || sede.getQualeAzienda() == null || sede.getQualeAzienda() <= 0)
            throw new IllegalArgumentException("Parametri non validi per la sede");
        if(sede.getVia() == null || sede.getVia().isEmpty()
                || sede.getCap() == null || sede.getCap().isEmpty()
                || sede.getCitta() == null || sede.getCitta().isEmpty()
                || sede.getRegione() == null || sede.getRegione().isEmpty()
                || sede.getCivico() == null || sede.getCivico().isEmpty())
            throw new IllegalArgumentException("Indirizzo non valido per la sede");
    }
}
