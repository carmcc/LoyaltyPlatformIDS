package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Prodotto;
import it.unicam.cs.ids.loyaltyplatform.service.ProdottoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController
{
    private final ProdottoService prodottoService;

    public ProdottoController(ProdottoService prodottoService)
    {
        this.prodottoService = prodottoService;
    }

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
        return this.prodottoService.addProdotto(prodotto);
    }

    @DeleteMapping("/deleteProdottoById/{id}")
    public void deleteProdottoById(@PathVariable("id") Integer id)
    {
       this.prodottoService.deleteProdottoById(id);
    }

    @PutMapping("/updateProdotto")
    public void updateProdotto(@RequestBody Prodotto prodotto)
    {
        this.prodottoService.updateProdotto(prodotto);
    }




}
