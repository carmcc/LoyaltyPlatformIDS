package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.BuonoSconto;
import it.unicam.cs.ids.loyaltyplatform.service.BuonoScontoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buonoSconto")
@AllArgsConstructor
public class BuonoScontoController {
    private final BuonoScontoService buonoScontoService;

    @GetMapping(value = "/getBuonoScontoById/{id}")
    public BuonoSconto getBuonoScontoById(@PathVariable("id") Integer id) {
        return this.buonoScontoService.getBuonoScontoById(id);
    }

    @GetMapping(value = "/getAllBuoniSconto")
    public List<BuonoSconto> getAllBuoniSconto() {
        return this.buonoScontoService.getAllBuoniSconto();
    }

    @PostMapping(value = "/addBuonoSconto")
    public BuonoSconto addBuonoSconto(@RequestBody BuonoSconto buonoSconto) {
        controlloValiditaBuonoSconto(buonoSconto);

        return this.buonoScontoService.addBuonoSconto(buonoSconto);
    }

    @DeleteMapping(value = "/deleteBuonoScontoById/{id}")
    public void deleteBuonoScontoById(@PathVariable("id") Integer id) {

        this.buonoScontoService.deleteBuonoScontoById(id);
    }

    @PutMapping(value = "/updateBuonoSconto")
    public void updateBuonoSconto(@RequestBody BuonoSconto buonoSconto) {
        controlloValiditaBuonoSconto(buonoSconto);

        this.buonoScontoService.updateBuonoSconto(buonoSconto);
    }

    private void controlloValiditaBuonoSconto(BuonoSconto buonoSconto) {
        if (buonoSconto == null)
            throw new NullPointerException("buono sconto è nullo");
        if(buonoSconto.getDataCreazione() == null || buonoSconto.getDataScadenza() == null)
            throw new NullPointerException("Almeno una delle due date assente nel buono sconto");
        if(buonoSconto.getValore() <= 0)
            throw new IllegalArgumentException("Valore del buono sconto non valido");
    }
}
