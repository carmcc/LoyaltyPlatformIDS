package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Ruolo;
import it.unicam.cs.ids.loyaltyplatform.repository.RuoloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuoloService {
    private final RuoloRepository ruoloRepository;
    public RuoloService(RuoloRepository ruoloRepository) {this.ruoloRepository = ruoloRepository;}
    public List<Ruolo> getRuoliByIdAzienda(Integer id) {
        return this.ruoloRepository.findAll().stream().filter(x->x.getQualeAccountAziendale()==id.intValue()).toList();
    }
    public List<Ruolo> getRuoliByAziendaAndPermesso(Integer id, String permesso) {
        return getRuoliByIdAzienda(id).stream().filter(x->x.getPermesso().equals(permesso)).toList();
    }
    public Optional<Ruolo> getRuoliByAziendaAndSeriale(Integer id, Integer seriale) {
        return getRuoliByIdAzienda(id).stream().filter(x->x.getQualeSeriale()==seriale.intValue()).findAny();
    }
    public List<Ruolo> getAllRuoli() {return this.ruoloRepository.findAll();}
    public Ruolo AddRecensione(Ruolo ruolo) {return this.ruoloRepository.save(ruolo);}
    public void deleteRecensione(Ruolo ruolo) {this.ruoloRepository.delete(ruolo);}
    public void updateRecensione(Ruolo ruolo) {this.ruoloRepository.saveAndFlush(ruolo);}
}
