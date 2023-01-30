package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Ruolo;
import it.unicam.cs.ids.loyaltyplatform.repository.RuoloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuoloService {
    private final RuoloRepository ruoloRepository;
    public List<Ruolo> getRuoliByIdAzienda(Integer id) {
        return this.ruoloRepository.findAll().stream().filter(x->x.getQualeAzienda()==id.intValue()).toList();
    }
    public List<Ruolo> getRuoliByAziendaAndPermesso(Integer id, String permesso) {
        return getRuoliByIdAzienda(id).stream().filter(x->x.getQualePermesso().equals(permesso)).toList();
    }
    public List<Ruolo> getRuoliByAziendaAndSeriale(Integer id, Integer seriale) {
        return this.ruoloRepository.findAll().stream()
                .filter(x->x.getQualeAzienda()==id.intValue() && x.getQualeSeriale()==seriale.intValue())
                .toList();
    }
    public Optional<Ruolo> getRuoloByAziendaSerialeAndPermesso(Integer idAzienda, Integer seriale, String idPermesso) {
        return this.ruoloRepository.findAll().stream()
                .filter(x->x.getQualeAzienda()==idAzienda.intValue() && x.getQualeSeriale()==seriale.intValue() &&
                        x.getQualePermesso().equals(idPermesso)).findAny();
    }
    public List<Ruolo> getAllRuoli() {return this.ruoloRepository.findAll();}
    public Ruolo addRuolo(Ruolo ruolo) {return this.ruoloRepository.save(ruolo);}
    public void deleteRuolo(Ruolo ruolo) {this.ruoloRepository.delete(ruolo);}
    public void updateRuolo(Ruolo ruolo) {this.ruoloRepository.saveAndFlush(ruolo);}
}
