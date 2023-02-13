package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Invito;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKInvito;
import it.unicam.cs.ids.loyaltyplatform.service.InvitoService;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityValidator;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/invito")
public class InvitoController extends EntityValidator {
    InvitoService invitoService;
    @GetMapping(value = "/getInvitiByIdAziendaInvitante/{id}")
    public List<Invito> getInvitiByIdAziendaInvitante(@PathVariable("id") Integer id) {
        return this.invitoService.getInvitiByIdAziendaInvitante(id);
    }
    @GetMapping(value = "/getInvitoByIdAziendaInvitanteAndIdAziendaInvitata/{idAziendaInvitante}&{idAziendaInvitata}")
    public Invito getInvitoByIdAziendaInvitanteAndIdAziendaInvitata(@PathVariable("idAziendaInvitante") Integer idAziendaInvitante,
                                                                    @PathVariable("idAziendaInvitata") Integer idAziendaInvitata) {
        return this.invitoService.getInvitoByIdAziendaInvitanteAndIdAziendaInvitata(new PKInvito(idAziendaInvitante, idAziendaInvitata));
    }
    @GetMapping(value = "/getInvitiByIdCoalizione/{id}")
    public List<Invito> getInvitiByIdCoalizione(@PathVariable("id") Integer id) {
        return this.invitoService.getInvitiByIdCoalizione(id);
    }

    @GetMapping(value = "/getAllInviti")
    public List<Invito> getAllInviti() {
        return this.invitoService.getAllInviti();
    }

    @PostMapping(value = "/addInvito")
    public Invito addInvito(@RequestBody Invito invito) {
        validateEntity(invito);
        if (getInvitoByIdAziendaInvitanteAndIdAziendaInvitata(invito.getQualeAziendaInvitante(),invito.getQualeAziendaInvitata())!=null)
            throw new IllegalArgumentException("ID del record da aggiungere già presente");
        return this.invitoService.addInvito(invito);
    }

    @DeleteMapping(value = "/deleteByInvitoId//{idAziendaInvitante}&{idAziendaInvitata}")
    public void deleteByInvitoId(@PathVariable("idAziendaInvitante") Integer idAziendaInvitante,
                                 @PathVariable("idAziendaInvitata") Integer idAziendaInvitata) {
        if(getInvitoByIdAziendaInvitanteAndIdAziendaInvitata(idAziendaInvitante,idAziendaInvitata)==null)
            throw new IllegalArgumentException("ID del record da eliminare non presente");
        this.invitoService.deleteByInvitoId(new PKInvito(idAziendaInvitante, idAziendaInvitata));
    }

    @DeleteMapping(value = "/deleteInvito")
    public void deleteInvito(@RequestBody Invito invito) {
        validateEntity(invito);
        if(getInvitoByIdAziendaInvitanteAndIdAziendaInvitata(invito.getQualeAziendaInvitante(),invito.getQualeAziendaInvitata())==null)
            throw new IllegalArgumentException("ID del record da eliminare non presente");
        this.invitoService.deleteInvito(invito);
    }

    @PutMapping(value = "/updateInvito")
    public void updateInvito(@RequestBody Invito invito) {
        validateEntity(invito);
        if (getInvitoByIdAziendaInvitanteAndIdAziendaInvitata(invito.getQualeAziendaInvitante(),invito.getQualeAziendaInvitata())==null)
            throw new IllegalArgumentException("ID del record da modificare non presente");
        this.invitoService.updateInvito(invito);
    }
}
