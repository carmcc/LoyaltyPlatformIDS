package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Coalizione;
import it.unicam.cs.ids.loyaltyplatform.service.CoalizioneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coalizione")
@AllArgsConstructor
public class CoalizioneController implements ValidateEntity{

    private final CoalizioneService coalizioneService;

    @GetMapping("/getCoalizioneById/{id}")
    public Coalizione getCoalizioneById(@PathVariable("id") Integer id) {
        return this.coalizioneService.getCoalizioneById(id);
    }

    @GetMapping("/getAllCoalizioni")
    public List<Coalizione> getAllCoalizioni() {
        return this.coalizioneService.getAllCoalizioni();
    }

    @PostMapping("/addCoalizione")
    public Coalizione addCoalizione(@RequestBody Coalizione coalizione) {
        validateEntity(coalizione);
        if(coalizione.getIdCoalizione() != null && getCoalizioneById(coalizione.getIdCoalizione()) != null)
            throw new IllegalArgumentException("il record da inserire esiste già");

        return this.coalizioneService.addCoalizione(coalizione);
    }

    @DeleteMapping("/deleteCoalizioneById/{id}")
    public void deleteCoalizioneById(@PathVariable("id") Integer id) {
        if(getCoalizioneById(id) == null)
            throw new IllegalArgumentException("il record da rimuovere non esiste");

        this.coalizioneService.deleteCoalizioneById(id);
    }

    @PutMapping("/updateCoalizione")
    public void updateCoalizione(@RequestBody Coalizione coalizione) {
        validateEntity(coalizione);
        if(coalizione.getIdCoalizione() == null)
            throw new NullPointerException("id coalizione non deve essere nullo per l'update");
        if(getCoalizioneById(coalizione.getIdCoalizione()) == null)
            throw new IllegalArgumentException("il record da aggiornare non esiste");

        this.coalizioneService.updateCoalizione(coalizione);
    }

    @Override
    public void validateEntity(Object entity) {
        Coalizione coalizione = (Coalizione) entity;
        if(coalizione == null)
            throw new NullPointerException("coalizione è nulla");
        if(coalizione.getParametroBuoniSpesa() <= 0)
            throw new IllegalArgumentException("parametro buoni spesa negativo o uguale a zero");
        if (coalizione.getPenalitaCondivisione() < 1)
            throw new IllegalArgumentException("penalità condivisione minore di 1");
        if(coalizione.getPercentualeRitiroPremi() < 0 || coalizione.getPercentualeRitiroPremi() >= 100)
            throw new IllegalArgumentException("percentuale ritiro premi non valida");

    }
}
