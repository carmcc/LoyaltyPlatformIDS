package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Prodotto;
import it.unicam.cs.ids.loyaltyplatform.repository.ProdottoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdottoService
{
    private final ProdottoRepository prodottoRepository;
    public Prodotto getProdottoById(Integer id)
    {
        return this.prodottoRepository.findById(id).orElse(null);
    }

    public List<Prodotto> getAllProdotti()
    {
        return this.prodottoRepository.findAll();
    }

    public Prodotto addProdotto(Prodotto prodotto)
    {
        return this.prodottoRepository.save(prodotto);
    }

    public void deleteProdottoById(Integer id)
    {
        this.prodottoRepository.deleteById(id);
    }

    public void updateProdotto(Prodotto prodotto)
    {
        this.prodottoRepository.saveAndFlush(prodotto);
    }

}
