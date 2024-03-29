package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Prodotto;
import it.unicam.cs.ids.loyaltyplatform.service.ProdottoService;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityValidator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotto")
@AllArgsConstructor
public class ProdottoController extends EntityValidator
{
    private final ProdottoService prodottoService;

    @GetMapping("/getProdottoById/{id}")
    public Prodotto getProdottoById(@PathVariable("id") Integer id)
    {
       return this.prodottoService.getProdottoById(id);
    }

    @GetMapping("/getAllProdotti")
    public List<Prodotto> getAllProdotti()
    {
        return this.prodottoService.getAllProdotti();
    }

    @PostMapping("/addProdotto")
    public Prodotto addProdotto(@RequestBody Prodotto prodotto)
    {
        validateEntity(prodotto);
        return this.prodottoService.addProdotto(prodotto);
    }

    @DeleteMapping("/deleteProdottoById/{id}")
    public void deleteProdottoById(@PathVariable("id") Integer id)
    {
        if(getProdottoById(id) == null)
            throw new IllegalArgumentException("il record da rimuovere non esiste");
       this.prodottoService.deleteProdottoById(id);
    }

    @PutMapping("/updateProdotto")
    public void updateProdotto(@RequestBody Prodotto prodotto)
    {
        validateEntity(prodotto);
        if(prodotto.getIdProdotto() == null)
            throw new IllegalArgumentException("id prodotto non deve essere nullo per l'update");
        if(getProdottoById(prodotto.getIdProdotto()) == null)
            throw new IllegalArgumentException("il record da aggiornare non esiste");
        this.prodottoService.updateProdotto(prodotto);
    }
}
