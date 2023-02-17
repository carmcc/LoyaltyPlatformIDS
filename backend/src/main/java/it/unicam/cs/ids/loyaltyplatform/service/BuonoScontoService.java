package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.entity.BuonoSconto;
import it.unicam.cs.ids.loyaltyplatform.entity.Coalizione;
import it.unicam.cs.ids.loyaltyplatform.repository.BuonoScontoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
        buonoSconto.setIdBuono(null);
        checkBuoniSconto(buonoSconto.getQualeConsumatore());
        return this.buonoScontoRepository.save(buonoSconto);
    }
    public void deleteBuonoScontoById(Integer id) {
        this.buonoScontoRepository.deleteById(id);
    }
    public void updateBuonoSconto(BuonoSconto buonoSconto) {
        checkBuoniSconto(buonoSconto.getQualeConsumatore());
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
        checkBuoniSconto(idConsumatore);
        Calendar dataCreazione = Calendar.getInstance(Locale.ITALY);
        Calendar dataScadenza = Calendar.getInstance(Locale.ITALY);
        dataScadenza.set(dataScadenza.get(Calendar.YEAR),dataScadenza.get(Calendar.MONTH),
                    dataScadenza.get(Calendar.DAY_OF_MONTH)+7); //tutti i buoni sconto durano 7 giorni

        Coalizione coalizione = this.coalizioneService.getCoalizioneById(azienda.getQualeCoalizione());

        List<Azienda> listaAziendeCoalizzate = this.aziendaService.getAziendeByIdCoalizione(coalizione.getIdCoalizione());
        int randomIndex = (int) Math.round( (listaAziendeCoalizzate.size()-1) * Math.random() );
        Azienda aziendaBersaglio = listaAziendeCoalizzate.get(randomIndex); //assegno il buono ad un'azienda casuale (compresa la stessa che crea il buono)

        float valore = spesa/coalizione.getParametroBuoniSpesa();

        return new BuonoSconto(null,idConsumatore,aziendaBersaglio.getIdAzienda(),dataCreazione.getTime(),dataScadenza.getTime(),valore);
    }

    /**
     * Questo metodo controlla se i buoni sconto di un consumatore sono scaduti e li elimina.
     *
     * @param idConsumatore Il consumatore di cui controllare i buoni sconto
     */
    private void checkBuoniSconto(Integer idConsumatore) {
        List<BuonoSconto> listaBuoniSconto = buonoScontoRepository.getBuonoScontoByQualeConsumatore(idConsumatore);
        for(BuonoSconto buonoSconto : listaBuoniSconto) {
                if(buonoSconto.getDataScadenza().before(Calendar.getInstance(Locale.ITALY).getTime())) {
                    this.deleteBuonoScontoById(buonoSconto.getIdBuono());
            }
        }
    }
}
