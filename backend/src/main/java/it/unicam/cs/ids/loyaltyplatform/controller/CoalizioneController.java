package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Coalizione;
import it.unicam.cs.ids.loyaltyplatform.service.CoalizioneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coalizione")
public class CoalizioneController {

    private final CoalizioneService coalizioneService;

    public CoalizioneController(CoalizioneService coalizioneService) {
        this.coalizioneService = coalizioneService;
    }
    @GetMapping("/getCoalisioneById/{id}")
    public Coalizione getCoalizioneById(@PathVariable("id") Integer id) {
        return this.coalizioneService.getCoalizioneById(id);
    }

    @GetMapping("/getAllCoalizioni")
    public List<Coalizione> getAllCoalizioni() {
        return this.coalizioneService.getAllCoalizioni();
    }

    @PostMapping("/addCoalizione")
    public Coalizione addCoalizione(Coalizione coalizione) {
        return this.coalizioneService.addCoalizione(coalizione);
    }

    @DeleteMapping("/deleteCoalizioneById/{id}")
    public void deleteCoalizioneById(@PathVariable("id") Integer id) {
        this.coalizioneService.deleteCoalizioneById(id);
    }

    @PutMapping("/updateCoalizione")
    public void updateCoalizione(Coalizione coalizione) {
        this.coalizioneService.updateCoalizione(coalizione);
    }
}
