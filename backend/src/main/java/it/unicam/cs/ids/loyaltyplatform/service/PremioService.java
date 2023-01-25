package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Premio;
import it.unicam.cs.ids.loyaltyplatform.repository.PremioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PremioService {
    private final PremioRepository premioRepository;
    public List<Premio> getPremiByIdAzienda(Integer id) {
        return this.premioRepository.findAll().stream().filter(x->x.getQualeAzienda()==id.intValue()).toList();
    }
    public List<Premio> getPremiByIdProdotto(Integer id) {
        return this.premioRepository.findAll().stream().filter(x -> x.getQualeProdotto() == id.intValue()).toList();
    }
    public Optional<Premio> getPremioByAziendaAndProdotto(Integer idAzienda, Integer idProdotto) {
        return this.premioRepository.findAll().stream()
                .filter(x->x.getQualeAzienda()==idAzienda.intValue() && x.getQualeProdotto()==idProdotto.intValue())
                .findAny();
    }
    public List<Premio> getAllPremi() {return this.premioRepository.findAll();}
    public Premio addPremio(Premio premio) {return this.premioRepository.save(premio);}
    public void deletePremio(Premio premio) {this.premioRepository.delete(premio);}
    public void updatePremio(Premio premio) {this.premioRepository.saveAndFlush(premio);}
}
