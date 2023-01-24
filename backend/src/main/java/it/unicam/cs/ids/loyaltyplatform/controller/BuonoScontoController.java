package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.BuonoSconto;
import it.unicam.cs.ids.loyaltyplatform.service.BuonoScontoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buonoSconto")
public class BuonoScontoController {
    private final BuonoScontoService buonoScontoService;
    public BuonoScontoController(BuonoScontoService buonoScontoService) {
        this.buonoScontoService = buonoScontoService;
    }

    @GetMapping(value = "/getBuonoScontoById/{id}")
    public BuonoSconto getBuonoScontoById(@PathVariable("id") Integer id) {
        return this.buonoScontoService.getBuonoScontoById(id);
    }

    @GetMapping(value = "/getAllBuoniSconto")
    public List<BuonoSconto> getAllBuoniSconto() {
        return this.buonoScontoService.getAllBuoniSconto();
    }

    //crea un azienda con metodo POST
    @PostMapping(value = "/addBuonoSconto")
    public BuonoSconto addBuonoSconto(@RequestBody BuonoSconto buonoSconto) {
        return this.buonoScontoService.addBuonoSconto(buonoSconto);
    }

    @DeleteMapping(value = "/deleteBuonoScontoById/{id}")
    public void deleteBuonoScontoById(@PathVariable("id") Integer id) {
        this.buonoScontoService.deleteBuonoScontoById(id);
    }

    @PutMapping(value = "/updateBuonoSconto")
    public void updateBuonoSconto(@RequestBody BuonoSconto buonoSconto) {
        this.buonoScontoService.updateBuonoSconto(buonoSconto);
    }
}
