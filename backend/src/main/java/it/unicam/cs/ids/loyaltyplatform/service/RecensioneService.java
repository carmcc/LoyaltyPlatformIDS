package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import it.unicam.cs.ids.loyaltyplatform.entity.Recensione;
import it.unicam.cs.ids.loyaltyplatform.repository.RecensioneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecensioneService {
    private final RecensioneRepository recensioneRepository;
    private final AdesioneService adesioneService;
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

    /**
     * Metodo che permette di scrivere una recensione
     * @param recensione Recensione da scrivere
     * @return Recensione scritta
     * @throws IllegalArgumentException
     */
    public Recensione writeRewiew(Recensione recensione)
    {
        Optional<Adesione> adesioneOptional = this.adesioneService.getAdesioneByConsumatoreAndAzienda(recensione.getQualeConsumatore(), recensione.getQualeAzienda());
        if(adesioneOptional.isEmpty())
            throw new IllegalArgumentException("Utente non risulta registrato come cliente dell'azienda");
        return recensione;
    }
}
