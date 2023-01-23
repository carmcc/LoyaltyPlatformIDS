package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Recensione;
import it.unicam.cs.ids.loyaltyplatform.repository.RecensioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecensioneService {
    final RecensioneRepository recensioneRepository;
    public RecensioneService(RecensioneRepository recensioneRepository) {this.recensioneRepository = recensioneRepository;}
    public List<Recensione> getRecensioniByIdConsumatore(Integer id) {
        return this.recensioneRepository.findAll().stream().filter(x->x.getQualeConsumatore()==id.intValue()).toList();
    }
    public List<Recensione> getRecensioniByIdAzienda(Integer id) {
        return this.recensioneRepository.findAll().stream().filter(x->x.getQualeAzienda()==id.intValue()).toList();
    }
    public Optional<Recensione> getRecensioneByConsumatoreAndAzienda(Integer idConsumatore, Integer idAzienda) {
        return this.recensioneRepository.findAll().stream()
                .filter(x-> x.getQualeConsumatore()==idConsumatore.intValue() && x.getQualeAzienda()==idAzienda.intValue())
                .findAny();
    }
    public List<Recensione> getAllRecensioni() {return this.recensioneRepository.findAll();}
    public Recensione AddRecensione(Recensione recensione) {return this.recensioneRepository.save(recensione);}
    public void deleteRecensione(Recensione recensione) {this.recensioneRepository.delete(recensione);}
    public void updateRecensione(Recensione recensione) {this.recensioneRepository.saveAndFlush(recensione);}
}
