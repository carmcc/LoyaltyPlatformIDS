package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.entity.Consumatore;
import it.unicam.cs.ids.loyaltyplatform.entity.Pagamento;
import it.unicam.cs.ids.loyaltyplatform.repository.AdesioneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdesioneService
{
    private final AdesioneRepository adesioneRepository;
    private final AziendaService aziendaService;
    private final ConsumatoreService consumatoreService;


    public List<Adesione> getAdesioniByIdConsumatore(Integer id) {
        return this.adesioneRepository.findAll().stream().filter(x->x.getQualeConsumatore()==id.intValue()).toList();
    }
    public List<Adesione> getAdesioniByIdAzienda(Integer id) {
        return this.adesioneRepository.findAll().stream().filter(x->x.getQualeAzienda()==id.intValue()).toList();
    }
    public Optional<Adesione> getAdesioneByConsumatoreAndAzienda(Integer idConsumatore, Integer idAzienda) {
        return this.adesioneRepository.findAll().stream()
                .filter(x -> x.getQualeConsumatore()==idConsumatore.intValue() && x.getQualeAzienda() == idAzienda.intValue())
                .findAny();
    }
    public List<Adesione> getAllAdesioni() {
        return this.adesioneRepository.findAll();
    }
    public Adesione addAdesione(Adesione adesione) {
        return this.adesioneRepository.save(adesione);
    }
    public void deleteAdesione(Adesione adesione) {
        this.adesioneRepository.delete(adesione);
    }
    public void updateAdesione(Adesione adesione) {
        this.adesioneRepository.saveAndFlush(adesione);
    }


    /**
     * Questo metodo incrementa i punti presenti nell'adesione del cliente aggiornando il database tramite la repository.
     *      Il metodo richiede al database i dati del consumatore, dell'azienda e dell'adesione.
     *
     * @param pagamento     Pagamento su cui si basa l'incremento dei punti
     *
     * @return  True se l'operazione è avvenuta.
     *          False se il consumatore non è aderito all'azienda in cui ha effettuato la spesa.
     */
    public Adesione incrementoPunti(Pagamento pagamento) {    //TODO rimuovi azienda dai parametri richiesti dopo aver aggiunto "qualeAzienda" a Pagamento
        Consumatore consumatore= this.consumatoreService.getConsumatoreById(pagamento.getQualeConsumatore());
        Azienda azienda = this.aziendaService.getAziendaById(pagamento.getQualeAzienda());

        Optional<Adesione> adesioneOptional = this.getAdesioneByConsumatoreAndAzienda(consumatore.getIdConsumatore(),azienda.getIdAzienda());    //ottengo l'adesione...
        if(adesioneOptional.isEmpty())
            return null;      //...e la controllo
        Adesione adesione = adesioneOptional.get();     //creo l'adesione copia da aggiornare

        float moltiplicatore = getValoriMoltSistemaLivelliAsIntegerList(azienda.getMoltSistemaLivelli())    //ottengo il moltiplicatore per il livello del cliente
                .get(adesione.getLivelloConsumatore()-1);
        float incremento = pagamento.getCostoTotale() * moltiplicatore;   //converto la spesa in punti
        if (adesione.getIsVip())
            incremento += pagamento.getCostoTotale() * azienda.getMoltiplicatoreVip();  //se il consumatore è VIP, aumento il valore dell'incremento
        int incrementoArrotondato = Math.round(incremento); //arrotondo i punti per poterli aggiungere

        adesione.setPuntiConsumatore(adesione.getPuntiConsumatore()+incrementoArrotondato);    //addiziono i punti al consumatore nell'adesione copia
        this.updateAdesione(adesione);   //sovrascrivo l'adesione nel database con quella aggiornata
        return adesione;
    }

    /**
     * Questo metodo prende in input la stringa moltSistemaLivelli, la quale rispetta una regex,
     *  per poi convertirla in una lista di numeri Float.
     *
     * @param moltSistemaLivelli    La stringa contenente i dati numerici
     *
     * @return  List<Float> dei dati numerici
     */
    private List<Float> getValoriMoltSistemaLivelliAsIntegerList(String moltSistemaLivelli) {
        String[] listaValoriSistemaLivelli = moltSistemaLivelli.split(","); //separo i valori "[X]"
        List<Float> listaValoriNumerici = new ArrayList<>();
        for (String valore: listaValoriSistemaLivelli) {
            valore = valore.substring(1,valore.length()-1);  //rimuovo le parentesi quadre
            listaValoriNumerici.add(Float.valueOf(valore)); //convergo le stringhe in Float e le inserisco nella lista
        }
        return listaValoriNumerici;
    }
}
