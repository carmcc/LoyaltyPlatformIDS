package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Permesso;
import it.unicam.cs.ids.loyaltyplatform.service.PermessoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permesso")
@AllArgsConstructor
public class PermessoController extends EntityValidator
{
    private final PermessoService permessoService;

    @GetMapping(value = "/getPermessoById/{id}")
    public Permesso getPermessoById(@PathVariable("id") String id) {
        return this.permessoService.getPermessoById(id);
    }

    @GetMapping(value = "/getAllPermessi")
    public List<Permesso> getAllPermessi() {
        return this.permessoService.getAllPermessi();
    }

    @PostMapping(value = "/addPermesso")
    public Permesso addPermesso(@RequestBody Permesso permesso) {
        validateEntity(permesso);
        if(getPermessoById(permesso.getNomePermesso()) != null)     //L'id non deve essere null perché è l'unica variabile che lo identifica
            throw new IllegalArgumentException("il record da inserire esiste già");

        return this.permessoService.addPermesso(permesso);
    }

    @DeleteMapping(value = "/deletePermessoById/{id}")
    public void deletePermessoById(@PathVariable("id") String id) {
        if(getPermessoById(id) == null)
            throw new IllegalArgumentException("il record da rimuovere non esiste");

        this.permessoService.deletePermessoById(id);
    }
}
