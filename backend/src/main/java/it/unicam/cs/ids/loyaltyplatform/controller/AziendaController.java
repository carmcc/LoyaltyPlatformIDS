package it.unicam.cs.ids.loyaltyplatform.controller;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/azienda")
@AllArgsConstructor
public class AziendaController extends EntityValidator
{
    private final AziendaService aziendaService;

    @GetMapping(value = "/getAziendaById/{id}")
    public Azienda getAziendaById(@PathVariable("id") Integer id) {
        return this.aziendaService.getAziendaById(id);
    }

    @GetMapping(value = "/getAllAziende")
    public List<Azienda> getAllAziende() {
        return this.aziendaService.getAllAziende();
    }

    //crea un azienda con metodo POST
    @PostMapping(value = "/addAzienda")
    public Azienda addAzienda(@RequestBody Azienda azienda) {
        validateEntity(azienda);
        if(azienda.getIdAzienda() != null && getAziendaById(azienda.getIdAzienda()) != null)
            throw new IllegalArgumentException("Il record da aggiungere esiste già");
        return this.aziendaService.addAzienda(azienda);
    }

    @DeleteMapping(value = "/deleteAziendaById/{id}")
    public void deleteAziendaById(@PathVariable("id") Integer id) {
        if(getAziendaById(id) == null)
            throw new NullPointerException("ID assente per la cancellazione dell'azienda");
        this.aziendaService.deleteAziendaById(id);
    }
    //aggiorna un azienda con metodo PUT
    @PutMapping(value = "/updateAzienda")
    public void updateAzienda(@RequestBody Azienda azienda) {
        validateEntity(azienda);
        if(azienda.getIdAzienda() == null)
            throw new NullPointerException("ID dell' azienda non deve essere nullo per l'update");
        if(getAziendaById(azienda.getIdAzienda()) == null)
            throw new IllegalArgumentException("ID del record da modificare non esiste");
        this.aziendaService.updateAzienda(azienda);
    }
}
