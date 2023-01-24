package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Sconto;
import it.unicam.cs.ids.loyaltyplatform.repository.ScontoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScontoService {
    private final ScontoRepository scontoRepository;
    public ScontoService(ScontoRepository scontoRepository) {this.scontoRepository = scontoRepository;}
    public List<Sconto> getScontiByIdAzienda(Integer id) {
        return this.scontoRepository.findAll().stream().filter(x->x.getQualeAzienda()==id.intValue()).toList();
    }
    public List<Sconto> getScontiByIdProdotto(Integer id) {
        return this.scontoRepository.findAll().stream().filter(x->x.getQualeProdotto()==id.intValue()).toList();
    }
    public Optional<Sconto> getScontoByAziendaAndProdotto(Integer idAzienda, Integer idProdotto) {
        return this.scontoRepository.findAll().stream()
                .filter(x-> x.getQualeAzienda()==idAzienda.intValue() && x.getQualeProdotto()==idProdotto.intValue())
                .findAny();
    }
    public List<Sconto> getAllSconti() {return this.scontoRepository.findAll();}
    public Sconto addSconto(Sconto sconto) {return this.scontoRepository.save(sconto);}
    public void deleteSconto(Sconto sconto) {this.scontoRepository.delete(sconto);}
    public void updateSconto(Sconto sconto) {this.scontoRepository.saveAndFlush(sconto);}
}
