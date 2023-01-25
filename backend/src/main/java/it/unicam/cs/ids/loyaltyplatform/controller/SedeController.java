package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Sede;
import it.unicam.cs.ids.loyaltyplatform.service.SedeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sede")
public class SedeController
{
    private final SedeService sedeService;

    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping(value = "/getSedeById/{id}")
    public Sede getSedeById(@PathVariable("id") Integer id) {
        return this.sedeService.getSedeById(id);
    }
    @GetMapping(value = "/getAllSedi")
    public List<Sede> getAllSedi() {
        return this.sedeService.getAllSedi();
    }

    @PostMapping(value = "/addSede")
    public Sede addSede(@RequestBody Sede sede) {
        return this.sedeService.addSede(sede);
    }

    @DeleteMapping(value = "/deleteSedeById/{id}")
    public void deleteSedeById(@PathVariable("id") Integer id) {
        this.sedeService.deleteSedeById(id);
    }

    @PutMapping(value = "/updateSede")
    public void updateSede(@RequestBody Sede sede) {
        this.sedeService.updateSede(sede);
    }

}
