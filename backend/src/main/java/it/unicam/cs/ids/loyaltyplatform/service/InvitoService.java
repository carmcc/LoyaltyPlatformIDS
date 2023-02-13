package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Invito;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKInvito;
import it.unicam.cs.ids.loyaltyplatform.repository.InvitoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class InvitoService {
    private final InvitoRepository invitoRepository;

    public List<Invito> getInvitiByIdAziendaInvitante(Integer id) {
        return this.invitoRepository.findByQualeAziendaInvitante(id);
    }
    public Invito getInvitoByIdAziendaInvitanteAndIdAziendaInvitata(PKInvito invito) {
        return this.invitoRepository.findById(invito).orElse(null);
    }
    public List<Invito> getInvitiByIdCoalizione(Integer id) {
        return this.invitoRepository.findByQualeCoalizione(id);
    }
    public List<Invito> getAllInviti() {
        return this.invitoRepository.findAll();
    }
    public Invito addInvito(Invito invito) {
        return this.invitoRepository.save(invito);
    }
    public void deleteByInvitoId(PKInvito id) {
        this.invitoRepository.deleteById(id);
    }

    public void deleteInvito(Invito invito) {
        this.invitoRepository.delete(invito);
    }
    public void updateInvito(Invito invito) {
        this.invitoRepository.saveAndFlush(invito);
    }
}
