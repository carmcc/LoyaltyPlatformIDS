package it.unicam.cs.ids.loyaltyplatform.service;


import it.unicam.cs.ids.loyaltyplatform.entity.Consumatore;
import it.unicam.cs.ids.loyaltyplatform.repository.ConsumatoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumatoreService {
    private final ConsumatoreRepository consumatoreRepository;

    public ConsumatoreService(ConsumatoreRepository consumatoreRepository)
    {
        this.consumatoreRepository = consumatoreRepository;
    }
    public Consumatore getConsumatoreById(Integer id)
    {
        return this.consumatoreRepository.findById(id).orElse(null);
    }

    public List<Consumatore> getAllConsumatori() {
        return this.consumatoreRepository.findAll();
    }
    public Consumatore addConsumatore(Consumatore consumatore) {
        return this.consumatoreRepository.save(consumatore);
    }
    public void deleteConsumatoreById(Integer id) {
        this.consumatoreRepository.deleteById(id);
    }
    public void updateConsumatore(Consumatore consumatore) {
        this.consumatoreRepository.saveAndFlush(consumatore);
    }



}
