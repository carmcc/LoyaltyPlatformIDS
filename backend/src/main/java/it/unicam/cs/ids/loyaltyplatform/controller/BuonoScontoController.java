package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.BuonoSconto;
import it.unicam.cs.ids.loyaltyplatform.service.BuonoScontoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buonoSconto")
@AllArgsConstructor
public class BuonoScontoController extends EntityValidator{
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
        validateEntity(buonoSconto);
        if(buonoSconto.getIdBuono() != null && getBuonoScontoById(buonoSconto.getIdBuono()) != null)
            throw new IllegalArgumentException("il record da aggiungere esiste già");
        return this.buonoScontoService.addBuonoSconto(buonoSconto);
    }

    @DeleteMapping(value = "/deleteBuonoScontoById/{id}")
    public void deleteBuonoScontoById(@PathVariable("id") Integer id) {
        if(getBuonoScontoById(id) == null)
            throw new IllegalArgumentException("il record da rimuovere non esiste");
        this.buonoScontoService.deleteBuonoScontoById(id);
    }

    @PutMapping(value = "/updateBuonoSconto")
    public void updateBuonoSconto(@RequestBody BuonoSconto buonoSconto) {
        validateEntity(buonoSconto);
        if(buonoSconto.getIdBuono() == null)
            throw new NullPointerException("id buono sconto non deve essere nullo per l'update");
        if(getBuonoScontoById(buonoSconto.getIdBuono()) == null)
            throw new IllegalArgumentException("il record da aggiornare non esiste");
        this.buonoScontoService.updateBuonoSconto(buonoSconto);
    }
}
