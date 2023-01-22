package it.unicam.cs.ids.loyaltyplatform.controller;
import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import it.unicam.cs.ids.loyaltyplatform.service.AdesioneService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/adesione")
public class AdesioneController
{
    private final AdesioneService adesioneService;

    public AdesioneController(AdesioneService adesioneService) {
        this.adesioneService = adesioneService;
    }

    @RequestMapping(value = "/getAdesioniByIdConsumatore/{id}", method = RequestMethod.GET)
    public List<Adesione> getAdesioniByIdConsumatore(@PathVariable("id") List<Integer> id) {
        return this.adesioneService.getAdesioniByIdConsumatore(id);
    }
    @RequestMapping(value = "/getAdesioneByIdConsumatoreAndIdAzienda/{idConsumatore}&{idAzienda}")
    public Adesione getAdesioneByIdConsumatoreAndIdAzienda(@PathVariable("idConsumatore") List<Integer> idConsumatore, @PathVariable("idAzienda") Integer idAzienda) {
        return this.adesioneService.getAdesioneByIdAzienda(getAdesioniByIdConsumatore(idConsumatore), idAzienda);
    }
    @RequestMapping(value = "/getAllAdesioni",method = RequestMethod.GET)
    public List<Adesione> getAllAdesioni() {
        return this.adesioneService.getAllAdesioni();
    }

    @RequestMapping(value = "/addAdesione", method = RequestMethod.POST)
    public Adesione addAdesione(Adesione adesione) {
        return this.adesioneService.addAdesione(adesione);
    }

    @RequestMapping(value = "/deleteAdesioneById/{id}", method = RequestMethod.DELETE)
    public void deleteAdesioneById(@PathVariable("id") Integer id) {
        this.adesioneService.deleteAdesioneById(id);
    }

    @RequestMapping(value = "/updateAdesione", method = RequestMethod.PUT)
    public void updateAdesione(Adesione adesione) {
        this.adesioneService.updateAdesione(adesione);
    }
}
