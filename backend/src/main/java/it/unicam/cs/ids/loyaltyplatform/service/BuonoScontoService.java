package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.entity.BuonoSconto;
import it.unicam.cs.ids.loyaltyplatform.entity.Coalizione;
import it.unicam.cs.ids.loyaltyplatform.repository.BuonoScontoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BuonoScontoService {
    private final BuonoScontoRepository buonoScontoRepository;
    private final AziendaService aziendaService;
    private final CoalizioneService coalizioneService;

    public BuonoSconto getBuonoScontoById(Integer id) {
        return this.buonoScontoRepository.findById(id).orElse(null);
    }
    public List<BuonoSconto> getAllBuoniSconto() {
        return this.buonoScontoRepository.findAll();
    }
    public BuonoSconto addBuonoSconto(BuonoSconto buonoSconto) {
        return this.buonoScontoRepository.save(buonoSconto);
    }
    public void deleteBuonoScontoById(Integer id) {
        this.buonoScontoRepository.deleteById(id);
    }
    public void updateBuonoSconto(BuonoSconto buonoSconto) {
        this.buonoScontoRepository.saveAndFlush(buonoSconto);
    }

    /**
     * Questo metodo crea un oggetto buono sconto attribuendogli un valore, una data di creazione e una di scadenza.
     *      Questo metodo richiede al DB i dati della coalizione e delle aziende partecipanti.
     *
     * @param idConsumatore Il consumatore che usufruirà del buono sconto
     * @param azienda L'azienda che crea il buono sconto
     * @param spesa La spesa effettuata, da cui si ricaveranno i punti
     */
    public BuonoSconto createNewBuonoSconto(Integer idConsumatore, Azienda azienda, Float spesa) {
        Date dataCreazione = new Date();
        Date dataScadenza = incrementoData(dataCreazione);
        Coalizione coalizione = this.coalizioneService.getCoalizioneById(azienda.getQualeCoalizione());

        List<Azienda> listaAziendeCoalizzate = this.aziendaService.getAziendeByIdCoalizione(coalizione.getIdCoalizione());
        int randomIndex = (int) Math.round( (listaAziendeCoalizzate.size()-1) * Math.random() );
        Azienda aziendaBersaglio = listaAziendeCoalizzate.get(randomIndex); //assegno il buono ad un'azienda casuale (compresa la stessa che crea il buono)

        float valore = spesa/coalizione.getParametroBuoniSpesa();

        return new BuonoSconto(null,idConsumatore,aziendaBersaglio.getIdAzienda(),dataCreazione,dataScadenza,valore);
    }

    /**
     * Questo metodo prende in input un oggetto di tipo Date e trasla la data di 7 giorni
     *
     * @param dataDaModificare Data di partenza
     * @return La data dopo il dato quantitativo di giorni
     */
    private Date incrementoData(Date dataDaModificare) {    //TODO trova un'alternativa a Date
        int intGiorno = dataDaModificare.getDate();
        int intMese = dataDaModificare.getMonth();     //il mese è decrementato di 1
        int intAnno = dataDaModificare.getYear();  //l'anno è decrementato di 1900
        intGiorno += 7; //eseguo la traslazione di 7 giorni
        switch (intMese)    //correggo un eventuale giorno inesistente
        {
            case 3, 5, 8, 10 -> {
                if (intGiorno > 30) {
                    intGiorno -= 30;
                    intMese += 1;
                }
            }
            case 1 -> { //la durata di Febbraio dipende dal bisestile
                if (intAnno % 4 == 0) {
                    if (intGiorno > 29) {
                        intGiorno -= 29;
                        intMese += 1;
                    }
                } else {
                    if (intGiorno > 28) {
                        intGiorno -= 28;
                        intMese += 1;
                    }
                }
            }
            default -> {
                if (intGiorno > 31) {
                    intGiorno -= 31;
                    intMese += 1;
                }
            }
        }
        if(intMese > 11)    //correggo un eventuale mese inesistente
        {
            intMese -= 12;
            intAnno += 1;
        }
        dataDaModificare.setDate(intGiorno);
        dataDaModificare.setMonth(intMese);
        dataDaModificare.setYear(intAnno);
        return dataDaModificare;
    }
}
