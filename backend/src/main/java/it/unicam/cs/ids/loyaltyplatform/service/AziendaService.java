package it.unicam.cs.ids.loyaltyplatform.service;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.repository.AziendaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AziendaService {

        private final AziendaRepository aziendaRepository;

        public AziendaService(AziendaRepository aziendaRepository) {
            this.aziendaRepository = aziendaRepository;
        }
        public Azienda getAziendaById(Integer id) {
            Optional<Azienda> azienda = this.aziendaRepository.findById(id);
            return azienda.orElse(null);
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

