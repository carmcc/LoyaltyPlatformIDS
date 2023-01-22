package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.BuonoSconto;
import it.unicam.cs.ids.loyaltyplatform.service.BuonoScontoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/BuonoSconto")
public class BuonoScontoController {
    private final BuonoScontoService buonoScontoService;
    public BuonoScontoController(BuonoScontoService buonoScontoService) {
        this.buonoScontoService = buonoScontoService;
    }

    @RequestMapping(value = "/getBuonoScontoById/{id}", method = RequestMethod.GET)
    public BuonoSconto getBuonoScontoById(@PathVariable("id") Integer id) {
        return this.buonoScontoService.getBuonoScontoById(id);
    }

    @RequestMapping(value = "/getAllBuoniSconto", method = RequestMethod.GET)
    public List<BuonoSconto> getAllBuoniSconto() {
        return this.buonoScontoService.getAllBuoniSconto();
    }

    //crea un azienda con metodo POST
    @RequestMapping(value = "/addBuonoSconto", method = RequestMethod.POST)
    public BuonoSconto addAzienda(BuonoSconto buonoSconto) {
        return this.buonoScontoService.addBuonoSconto(buonoSconto);
    }

    @RequestMapping(value = "/deleteBuonoScontoById/{id}", method = RequestMethod.DELETE)
    public void deleteBuonoScontoById(@PathVariable("id") Integer id) {
        this.buonoScontoService.deleteBuonoScontoById(id);
    }

    //aggiorna un azienda con metodo PUT
    @RequestMapping(value = "/updateBuonoSconto", method = RequestMethod.PUT)
    public void updateBuonoSconto(BuonoSconto buonoSconto) {
        this.buonoScontoService.updateBuonoSconto(buonoSconto);
    }
}
