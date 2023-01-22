package it.unicam.cs.ids.loyaltyplatform.service;
import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import it.unicam.cs.ids.loyaltyplatform.repository.AdesioneRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdesioneService
{
    private final AdesioneRepository adesioneRepository;
    public AdesioneService(AdesioneRepository adesioneRepository) {
        this.adesioneRepository = adesioneRepository;
    }
    public List<Adesione> getAdesioniByIdConsumatore(List<Integer> id) {return this.adesioneRepository.findAllById(id);}
    public Adesione getAdesioneByIdAzienda(List<Adesione> listaAdesioni, Integer id) {return listaAdesioni.stream().filter(x -> x.getQualeAzienda() == id.intValue()).toList().get(0);}
    public List<Adesione> getAllAdesioni() {
        return this.adesioneRepository.findAll();
    }
    public Adesione addAdesione(Adesione adesione) {
        return this.adesioneRepository.save(adesione);
    }
    public void deleteAdesioneById(Integer id) {
        this.adesioneRepository.deleteById(id);
    }
    public void updateAdesione(Adesione adesione) {
        this.adesioneRepository.saveAndFlush(adesione);
    }
}
