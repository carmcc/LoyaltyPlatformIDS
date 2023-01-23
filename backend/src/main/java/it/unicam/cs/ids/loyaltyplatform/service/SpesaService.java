package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Spesa;
import it.unicam.cs.ids.loyaltyplatform.repository.SpesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpesaService {
    private final SpesaRepository spesaRepository;
    public SpesaService(SpesaRepository spesaRepository) {this.spesaRepository = spesaRepository;}
    public List<Spesa> getSpeseByIdPagamento(Integer id) {
        return this.spesaRepository.findAll().stream().filter(x->x.getQualePagamento()==id.intValue()).toList();
    }
    public List<Spesa> getSpeseByIdProdotto(Integer id) {
        return this.spesaRepository.findAll().stream().filter(x->x.getQualeProdotto()==id.intValue()).toList();
    }
    public Optional<Spesa> getSpesaByPagamentoAndProdotto(Integer idPagamento, Integer idProdotto) {
        return this.spesaRepository.findAll().stream()
                .filter(x-> x.getQualePagamento()==idPagamento.intValue() && x.getQualeProdotto()==idProdotto.intValue())
                .findAny();
    }
    public List<Spesa> getAllSpese() {return this.spesaRepository.findAll();}
    public Spesa AddSpesa(Spesa spesa) {return this.spesaRepository.save(spesa);}
    public void deleteSpesa(Spesa spesa) {this.spesaRepository.delete(spesa);}
    public void updateSpesa(Spesa spesa) {this.spesaRepository.saveAndFlush(spesa);}
}
