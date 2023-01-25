package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Permesso;
import it.unicam.cs.ids.loyaltyplatform.repository.PermessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PermessoService
{
    private final PermessoRepository permessoRepository;
    public Permesso getPermessoById(String id) {
        return this.permessoRepository.findById(id).orElse(null);
    }

    public List<Permesso> getAllPermessi() {
        return this.permessoRepository.findAll();
    }

    public Permesso addPermesso(Permesso permesso) {
        return this.permessoRepository.save(permesso);
    }

    public void deletePermessoById(String id) {
        this.permessoRepository.deleteById(id);
    }
}
