package it.unicam.cs.ids.loyaltyplatform.controller;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.entity.Coalizione;
import it.unicam.cs.ids.loyaltyplatform.entity.Invito;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKInvito;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.service.CoalizioneService;
import it.unicam.cs.ids.loyaltyplatform.service.InvitoService;
import it.unicam.cs.ids.loyaltyplatform.service.QRCodeService;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/azienda")
@AllArgsConstructor
public class AziendaController extends EntityValidator {
    private final AziendaService aziendaService;
    private final QRCodeService qrCodeService;
    private final InvitoService invitoService;
    private final CoalizioneService coalizioneService;

    @GetMapping(value = "/getAziendaById/{id}")
    public Azienda getAziendaById(@PathVariable("id") Integer id) {
        return this.aziendaService.getAziendaById(id);
    }

    @GetMapping(value = "/getAziendeByIdCoalizione/{id}")
    public List<Azienda> getAziendeByIdCoalizione(@PathVariable("id") Integer id) {
        return this.aziendaService.getAziendeByIdCoalizione(id);
    }

    @GetMapping(value = "/getAziendeCoalizzate/{id_coalizione}")
    public List<Azienda> getAziendeCoalizzate(@PathVariable("id_coalizione") Integer id) {
        return this.aziendaService.getAziendeCoalizzate(id);
    }

    @GetMapping(value = "/getAllAziende")
    public List<Azienda> getAllAziende() {
        return this.aziendaService.getAllAziende();
    }

    @PostMapping(value = "/addAzienda")
    public Azienda addAzienda(@RequestBody Azienda azienda) {
        validateEntity(azienda);
        return this.aziendaService.addAzienda(azienda);
    }

    @DeleteMapping(value = "/deleteAziendaById/{id}")
    public void deleteAziendaById(@PathVariable("id") Integer id) {
        if(getAziendaById(id) == null)
            throw new NullPointerException("ID assente per la cancellazione dell'azienda");
        this.aziendaService.deleteAziendaById(id);
    }
    //aggiorna un azienda con metodo PUT
    @PutMapping(value = "/updateAzienda")
    public void updateAzienda(@RequestBody Azienda azienda) {
        validateEntity(azienda);
        if(azienda.getIdAzienda() == null)
            throw new NullPointerException("ID dell' azienda non deve essere nullo per l'update");
        if(getAziendaById(azienda.getIdAzienda()) == null)
            throw new IllegalArgumentException("ID del record da modificare non esiste");
        this.aziendaService.updateAzienda(azienda);
    }

    @GetMapping(value = "/referral/{id}")
    public ResponseEntity<BufferedImage> referral(@PathVariable("id") Integer id) throws Exception {
        Azienda azienda = getAziendaById(id);
        if(azienda == null)
            throw new IllegalArgumentException("Azienda non trovata");
        return qrCodeService.qrCodeGenerator(azienda.getReferral());
    }

    /**
        Un azienda crea la coalizione ed invita un'altra azienda

        @param coalizione Coalizione da creare
        @param idAziendaInvitante ID dell'azienda che invita
        @param idAziendaInvitata ID dell'azienda invitata
     **/
    @PostMapping(value = "/createCoalizioneAndInvito/{idAziendaInvitante}&{idAziendaInvitata}")
    public void createCoalizioneAndInvito(@RequestBody Coalizione coalizione,
                                          @PathVariable("idAziendaInvitante") Integer idAziendaInvitante,
                                          @PathVariable("idAziendaInvitata") Integer idAziendaInvitata)
    {
        validateEntity(coalizione);
        coalizioneService.addCoalizione(coalizione);
        invitoService.addInvito(new Invito(idAziendaInvitante, idAziendaInvitata, coalizione.getIdCoalizione(), null));
    }

    @PostMapping(value = "/createInvito/{idCoalizione}&{idAziendaInvitante}&{idAziendaInvitata}")
    public void createInvito(@PathVariable("idCoalizione") Integer idCoalizione,
                             @PathVariable("idAziendaInvitante") Integer idAziendaInvitante,
                             @PathVariable("idAziendaInvitata") Integer idAziendaInvitata)
    {
        if(aziendaService.getAziendaById(idAziendaInvitante).getQualeCoalizione() != null)
            if(Objects.equals(aziendaService.getAziendaById(idAziendaInvitante).getQualeCoalizione(), idCoalizione))
                throw new IllegalArgumentException("Non puoi invitare in coalizione di cui non fai parte");
        invitoService.addInvito(new Invito(idAziendaInvitante, idAziendaInvitata, idCoalizione, null));
    }

    @PutMapping(value = "/responseInvito/{idAziendaInvitante}&{idAziendaInvitata}&{stato}")
    public void responseInvito(@PathVariable("idAziendaInvitante") Integer idAziendaInvitante,
                               @PathVariable("idAziendaInvitata") Integer idAziendaInvitata,
                               @PathVariable("stato") Boolean response)
    {
        Invito invito = invitoService.getInvitoByIdAziendaInvitanteAndIdAziendaInvitata(new PKInvito(idAziendaInvitante, idAziendaInvitata));
        if(invito == null)
            throw new IllegalArgumentException("Invito non trovato");
        if(aziendaService.getAziendaById(idAziendaInvitata).getQualeCoalizione() != null)
            throw new IllegalArgumentException("L'azienda è già in coalizione, non vuoi accettere l'invito");
        if(response) {
            aziendaService.getAziendaById(idAziendaInvitata).setQualeCoalizione(invito.getQualeCoalizione());
            aziendaService.getAziendaById(idAziendaInvitante).setQualeCoalizione(invito.getQualeCoalizione());
        }
        else{
            if(aziendaService.getAziendeByIdCoalizione(invito.getQualeCoalizione()).size()< 2)
                coalizioneService.deleteCoalizioneById(invito.getQualeCoalizione());
        }
    }

}
