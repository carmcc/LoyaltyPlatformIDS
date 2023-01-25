package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.BuonoSconto;
import it.unicam.cs.ids.loyaltyplatform.repository.BuonoScontoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BuonoScontoService {
    private final BuonoScontoRepository buonoScontoRepository;

    public BuonoSconto getBuonoScontoById(Integer id) {
        return this.buonoScontoRepository.findById(id).orElse(null);
    }
    public List<BuonoSconto> getAllBuoniSconto() {
        return this.buonoScontoRepository.findAll();
    }
    public BuonoSconto addBuonoSconto(BuonoSconto buonoSconto) {
        return this.buonoScontoRepository.save(buonoSconto);
    }
    public void deleteBuonoScontoById(Integer id) {
        this.buonoScontoRepository.deleteById(id);
    }
    public void updateBuonoSconto(BuonoSconto buonoSconto) {
        this.buonoScontoRepository.saveAndFlush(buonoSconto);
    }
}
