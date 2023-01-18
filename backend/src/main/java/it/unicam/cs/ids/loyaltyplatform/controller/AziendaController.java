package it.unicam.cs.ids.loyaltyplatform.controller;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/azienda")
public class AziendaController
{
    private final AziendaService aziendaService;
    public AziendaController(AziendaService aziendaService) {
        this.aziendaService = aziendaService;
    }

    @RequestMapping(value = "/getAziendaById/{id}", method = RequestMethod.GET)
    public Azienda getAziendaById(@PathVariable("id") Integer id) {
        return this.aziendaService.getAziendaById(id);
    }

    @RequestMapping(value = "/getAllAziende", method = RequestMethod.GET)
    public List<Azienda> getAllAziende() {
        return this.aziendaService.getAllAziende();
    }

    //crea un azienda con metodo POST
    @RequestMapping(value = "/addAzienda", method = RequestMethod.POST)
    public Azienda addAzienda(Azienda azienda) {
        return this.aziendaService.addAzienda(azienda);
    }

    @RequestMapping(value = "/deleteAziendaById/{id}", method = RequestMethod.DELETE)
    public void deleteAziendaById(@PathVariable("id") Integer id) {
        this.aziendaService.deleteAziendaById(id);
    }

    //aggiorna un azienda con metodo PUT
    @RequestMapping(value = "/updateAzienda", method = RequestMethod.PUT)
    public void updateAzienda(Azienda azienda) {
        this.aziendaService.updateAzienda(azienda);
    }

}
