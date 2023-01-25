package it.unicam.cs.ids.loyaltyplatform.service;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.repository.AziendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AziendaService {
    private final AziendaRepository aziendaRepository;
    public Azienda getAziendaById(Integer id) {
            return this.aziendaRepository.findById(id).orElse(null);
        }
    public List<Azienda> getAllAziende() {
            return this.aziendaRepository.findAll();
        }
    public Azienda addAzienda(Azienda azienda) {
            return this.aziendaRepository.save(azienda);
        }
    public void deleteAziendaById(Integer id) {
            this.aziendaRepository.deleteById(id);
        }
    public void updateAzienda(Azienda azienda) {
            this.aziendaRepository.saveAndFlush(azienda);
        }

}

