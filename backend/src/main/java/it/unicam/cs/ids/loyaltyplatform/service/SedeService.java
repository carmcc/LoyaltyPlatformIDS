package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Sede;
import it.unicam.cs.ids.loyaltyplatform.repository.SedeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedeService
{
    private final SedeRepository sedeRepository;
    public SedeService(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    public Sede getSedeById(Integer id) {
        return this.sedeRepository.findById(id).orElse(null);
    }

    public List<Sede> getAllSedi() {
        return this.sedeRepository.findAll();
    }

    public Sede addSede(Sede sede) {
        return this.sedeRepository.save(sede);
    }

    public void deleteSedeById(Integer id) {
        this.sedeRepository.deleteById(id);
    }

    public void updateSede(Sede sede) {
        this.sedeRepository.saveAndFlush(sede);
    }
}
