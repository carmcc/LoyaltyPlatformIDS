package it.unicam.cs.ids.loyaltyplatform.controller;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/azienda")
public class AziendaController
{
    private final AziendaService aziendaService;
    public AziendaController(AziendaService aziendaService) {
        this.aziendaService = aziendaService;
    }

    @GetMapping(value = "/getAziendaById/{id}")
    public Azienda getAziendaById(@PathVariable("id") Integer id) {
        return this.aziendaService.getAziendaById(id);
    }

    @GetMapping(value = "/getAllAziende")
    public List<Azienda> getAllAziende() {
        return this.aziendaService.getAllAziende();
    }

    //crea un azienda con metodo POST
    @RequestMapping(value = "/addAzienda", method = RequestMethod.POST)
    public Azienda addAzienda(@RequestBody Azienda azienda) {
        return this.aziendaService.addAzienda(azienda);
    }

    @DeleteMapping(value = "/deleteAziendaById/{id}")
    public void deleteAziendaById(@PathVariable("id") Integer id) {
        this.aziendaService.deleteAziendaById(id);
    }

    //aggiorna un azienda con metodo PUT
    @PutMapping(value = "/updateAzienda")
    public void updateAzienda(@RequestBody Azienda azienda) {
        this.aziendaService.updateAzienda(azienda);
    }

}
