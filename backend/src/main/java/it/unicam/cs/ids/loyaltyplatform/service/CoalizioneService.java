package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Coalizione;
import it.unicam.cs.ids.loyaltyplatform.repository.CoalizioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoalizioneService {
    private final CoalizioneRepository coalizioneRepository;

    public CoalizioneService(CoalizioneRepository coalizioneRepository) {
        this.coalizioneRepository = coalizioneRepository;
    }

    public Coalizione getCoalizioneById(Integer id) {
        return coalizioneRepository.findById(id).orElse(null);
    }

    public List<Coalizione> getAllCoalizioni() {
        return coalizioneRepository.findAll();
    }

    public Coalizione addCoalizione(Coalizione coalizione) {
        return coalizioneRepository.save(coalizione);
    }

    public void deleteCoalizioneById(Integer id) {
        coalizioneRepository.deleteById(id);
    }

    public void updateCoalizione(Coalizione coalizione) {
        coalizioneRepository.saveAndFlush(coalizione);
    }
}