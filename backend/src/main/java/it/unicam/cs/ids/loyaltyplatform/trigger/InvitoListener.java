package it.unicam.cs.ids.loyaltyplatform.trigger;

import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.entity.Invito;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKInvito;
import it.unicam.cs.ids.loyaltyplatform.repository.AziendaRepository;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.service.CoalizioneService;
import it.unicam.cs.ids.loyaltyplatform.service.InvitoService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@NoArgsConstructor
@AllArgsConstructor
@Service
public class InvitoListener {
    private AziendaService aziendaService;
    private CoalizioneService coalizioneService;
    private InvitoService invitoService;

//    @Autowired
//    public InvitoListener(AziendaService aziendaService, CoalizioneService coalizioneService, InvitoService invitoService) {
//        this.aziendaService = aziendaService;
//        this.coalizioneService = coalizioneService;
//        this.invitoService = invitoService;
//    }


    @PostUpdate
    @EventListener
    public void postUpdate(Invito invito){
        System.out.println(aziendaService);
        Azienda aziendaInvitante = aziendaService.getAziendaById(invito.getQualeAziendaInvitante());
        Azienda aziendaInvitata = aziendaService.getAziendaById(invito.getQualeAziendaInvitata());
        if(invito.getStato()) {
            if((aziendaInvitante.getQualeCoalizione() != null && !aziendaInvitante.getQualeCoalizione().equals(invito.getQualeCoalizione()))
            || aziendaInvitata.getQualeCoalizione() != null) {
                //TODO: invito deve essere cancellato quando non piu utile
                throw new IllegalStateException("La azienda invitata appartiene gia' a una coalizione oppure l'Azienda invitante non appartiene alla coalizione indicata");
            }
            aziendaService.updateAzienda(aziendaInvitante);
            aziendaService.updateAzienda(aziendaInvitata);
        }
        else {
            if(aziendaService.getAziendeCoalizzate(invito.getQualeCoalizione()).size() < 2) {
                coalizioneService.deleteCoalizioneById(invito.getQualeCoalizione());
            }
        }
        invitoService.deleteByInvitoId(new PKInvito(invito.getQualeAziendaInvitante(), invito.getQualeAziendaInvitata()));
    }
}

//            if(aziendaInvitante.getQualeCoalizione() == null && aziendaInvitata.getQualeCoalizione() == null) {
//                aziendaInvitante.setQualeCoalizione(invito.getQualeCoalizione());
//                aziendaInvitata.setQualeCoalizione(invito.getQualeCoalizione());
//                aziendaService.updateAzienda(aziendaInvitante);
//                aziendaService.updateAzienda(aziendaInvitata);
//            } else if (Objects.equals(aziendaInvitante.getQualeCoalizione(), invito.getQualeCoalizione()) && aziendaInvitata.getQualeCoalizione() == null ) {
//                aziendaInvitata.setQualeCoalizione(invito.getQualeCoalizione());
//                aziendaService.updateAzienda(aziendaInvitata);
//            }else{
//                throw new IllegalArgumentException("La azienda invitata appartiene gia' a una coalizione oppure l'Azienda invitante non appartiene alla coalizione indicata");
//            }
