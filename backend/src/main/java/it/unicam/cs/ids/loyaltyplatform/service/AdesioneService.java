package it.unicam.cs.ids.loyaltyplatform.service;
import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import it.unicam.cs.ids.loyaltyplatform.repository.AdesioneRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdesioneService
{
    private final AdesioneRepository adesioneRepository;
    public AdesioneService(AdesioneRepository adesioneRepository) {
        this.adesioneRepository = adesioneRepository;
    }
    public List<Adesione> getAdesioniByIdConsumatore(Integer id) {
        return this.adesioneRepository.findAll().stream().filter(x->x.getQualeConsumatore()==id.intValue()).toList();
    }
    public List<Adesione> getAdesioniByIdAzienda(Integer id) {
        return this.adesioneRepository.findAll().stream().filter(x->x.getQualeAzienda()==id.intValue()).toList();
    }
    public Optional<Adesione> getAdesioneByConsumatoreAndAzienda(Integer idConsumatore, Integer idAzienda) {
        return this.adesioneRepository.findAll().stream()
                .filter(x -> x.getQualeConsumatore()==idConsumatore.intValue() && x.getQualeAzienda() == idAzienda.intValue())
                .findAny();
    }
    public List<Adesione> getAllAdesioni() {
        return this.adesioneRepository.findAll();
    }
    public Adesione addAdesione(Adesione adesione) {
        return this.adesioneRepository.save(adesione);
    }
    public void deleteAdesione(Adesione adesione) {
        this.adesioneRepository.delete(adesione);
    }
    public void updateAdesione(Adesione adesione) {
        this.adesioneRepository.saveAndFlush(adesione);
    }
}
