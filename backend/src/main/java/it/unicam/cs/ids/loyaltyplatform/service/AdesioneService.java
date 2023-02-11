package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.*;
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
    private final CoalizioneService coalizioneService;


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
     * Questo metodo incrementa i punti presenti e l'esperienza nell'adesione del cliente aggiornando il database tramite la repository.
     *      Il metodo richiede al database i dati del consumatore, dell'azienda e dell'adesione.
     *
     * @param pagamento     Pagamento su cui si basa l'incremento dei punti
     *
     * @return  L'adesione aggiornata se l'operazione è avvenuta.
     *          Null se il consumatore non è aderito all'azienda in cui ha effettuato la spesa.
     */
    public Adesione incrementoPuntiEdEsperienza(Pagamento pagamento) {
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
        int esperienzaRichiestaLvlUp = 250 * adesione.getLivelloConsumatore();

        adesione.setPuntiConsumatore(adesione.getPuntiConsumatore() + incrementoArrotondato);    //addiziono i punti al consumatore nell'adesione copia
        adesione.setEsperienzaConsumatore(adesione.getEsperienzaConsumatore() + incrementoArrotondato);
        if(adesione.getEsperienzaConsumatore() >= esperienzaRichiestaLvlUp) //controllo se il consumatore può salire di livello
        {
            adesione.setEsperienzaConsumatore(adesione.getEsperienzaConsumatore()-esperienzaRichiestaLvlUp);
            adesione.setLivelloConsumatore(adesione.getLivelloConsumatore()+1);
        }
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

    /**
     * Questo metodo sottrae i punti in possesso del consumatore al fine di ritirare un dato premio.
     *      Se l'adesione del consumatore con l'azienda da cui ritira il premio ha punti a sufficienza,
     *          allora si sottraggono i punti normalmente.
     *      In caso di punti insufficienti sarà possibile utilizzare i punti di altre adesioni, ma solo se
     *          tali adesioni appartengono ad aziende facenti parte della medesima coalizione.
     *              In oltre tale coalizione deve avere la condivisione dei punti abilitata.
     *      Completata l'operazione il database verrà aggiornato tramite la repository.
     *      Il metodo richiede al database i dati del Consumatore, della coalizione,
     *          di tutte le aziende facenti parte della coalizione e di tutte le adesioni tra queste aziende e il consumatore.
     *
     * @param consumatore   Il consumatore che richiede di ritirare il premio.
     * @param premio    Il premio che verrà ritirato e da cui estrarre il valore in punti da sottrarre al cliente.
     *
     * @return  la lista delle adesioni aggiornate
     *
     * @throws IllegalArgumentException se il consumatore non ha aderito all'azienda, e se il consumatore non ha abbastanza punti
     *                                      senza che l'azienda faccia parte di un'adesione con la condivisione punti abilitata.
     */
    public List<Adesione> sottrazionePuntiPremio(Consumatore consumatore, Premio premio) {
        Azienda aziendaPrincipale = this.aziendaService.getAziendaById(premio.getQualeAzienda()); //azienda che esegue la consegna del premio
        Optional<Adesione> adesioneOptional = this.getAdesioneByConsumatoreAndAzienda(consumatore.getIdConsumatore(), aziendaPrincipale.getIdAzienda());
        if(adesioneOptional.isEmpty())  //se il consumatore non ha un'adesione con l'azienda, l'operazione termina
            throw new IllegalArgumentException("il consumatore non ha aderito all'azienda");

        Adesione adesionePrincipale = adesioneOptional.get();   //adesione prioritaria da cui prelevare i punti
        int costo = premio.getCosto();
        if(adesionePrincipale.getPuntiConsumatore() >= costo)   //l'adesione è in grado di pagare il costo per intero
        {
            adesionePrincipale.setPuntiConsumatore(adesionePrincipale.getPuntiConsumatore() - costo);
            this.updateAdesione(adesionePrincipale);    //aggiorno il DB
            List<Adesione> output = new ArrayList<>();
            output.add(adesionePrincipale);
            return output;
        }
        //è necessario utilizzare la funzionalità della condivisione punti

        Coalizione coalizione = this.coalizioneService.getCoalizioneById(aziendaPrincipale.getQualeCoalizione());
        if(coalizione == null)  //non esiste alcuna coalizione da cui richiedere punti
            throw new IllegalArgumentException("punti insufficienti per pagare il premio (nessuna coalizione rilevata)");
        if(!coalizione.getCondivisionePunti())  //la condivisione punti non è abilitata
            throw new IllegalArgumentException("punti insufficienti per pagare il premio (condivisione punti non abilitata)");
        if( (100 / (costo / adesionePrincipale.getPuntiConsumatore())) < coalizione.getPercentualeRitiroPremi())    //è necessario che si abbia almeno una certa percentuale del prezzo del premio per utilizzare la condivisione punti
            throw new IllegalArgumentException("rapporto costoPremio/puntiDisponibili troppo basso per abilitare la condivisione punti");

        //tutti i controlli sono stati fatti, quindi si procede a modificare i dati
        List<Adesione> listaAdesioniCoalizzate = getListaAdesioniByCoalizioneAndIdConsumatore(coalizione, consumatore.getIdConsumatore());
        costo = Math.round( (costo - adesionePrincipale.getPuntiConsumatore()) * coalizione.getPenalitaCondivisione() );    //calcolo il costo da distribuire tra le altre adesioni
        listaAdesioniCoalizzate = listaAdesioniCoalizzate.stream()
                .filter(x->x.getPuntiConsumatore() > 0).toList();  //rimuovo tutte le adesioni con zero punti perché non verranno modificate
        listaAdesioniCoalizzate = listaAdesioniCoalizzate.stream()
                                    .peek(x->{if(x.equals(adesionePrincipale))
                                                    x.setPuntiConsumatore(0);}).toList();   //azzero il valore dell'adesione principale nella lista, perché quella istanziata non verrà più usata

        if(costo > this.getSommaPuntiAdesioni(listaAdesioniCoalizzate))
            throw new IllegalArgumentException("punti insufficienti per pagare il premio (anche considerando i punti delle adesioni del cliente con altre aziende coalizzate)");

        return getListaAdesioniConSottrazioneDistribuita(costo, listaAdesioniCoalizzate);
    }

    private List<Adesione> getListaAdesioniByCoalizioneAndIdConsumatore(Coalizione coalizione, Integer idConsumatore) {
        return this.adesioneRepository.findAll().stream()
                .filter(x->this.aziendaService.getAziendaById(x.getQualeAzienda()).getQualeCoalizione() == coalizione.getIdCoalizione().intValue()) //considero solo le adesioni riguardanti aziende della coalizione...
                .filter(x->x.getQualeConsumatore() == idConsumatore.intValue()) //... e riguardanti il consumatore
                .toList();
    }
    private int getSommaPuntiAdesioni(List<Adesione> listaAdesioni) {
        int somma = 0;
        for (Adesione a : listaAdesioni) {
            somma += a.getPuntiConsumatore();
        }
        return somma;
    }
    private List<Adesione> getListaAdesioniConSottrazioneDistribuita(int costo, List<Adesione> listaAdesioni) {
        if(costo == 0)
            return listaAdesioni; //esco dal metodo senza apportare nessuna modifica alla lista
        int cardinalitaValoriMaggioriDiZero = getCardinalitaValoriMaggioriDiZero(listaAdesioni);
        int valoreMinimo = getValoreMinimo(listaAdesioni);
        int costoSuddiviso = costo / cardinalitaValoriMaggioriDiZero;
        if(valoreMinimo >= costoSuddiviso)   //tutte le adesioni possono permettersi di pagare il costo
        {
            int resto = costo - (costoSuddiviso * cardinalitaValoriMaggioriDiZero);
            for (Adesione a: listaAdesioni) {
                if(a.getPuntiConsumatore()==0)
                    continue;
                a.setPuntiConsumatore(a.getPuntiConsumatore() - costoSuddiviso);
            }
            if(resto==0)    //se non c'è resto allora è stato pagato tutto
                return listaAdesioni;
            //le adesioni col valore più alto si prenderanno carico del resto
            int valoreMassimo = getValoreMassimo(listaAdesioni);
            if(valoreMassimo >= resto)  //esiste un'adesione in grado di prendersi carico del resto
            {
                Adesione adesioneDaModificare = listaAdesioni.stream().filter(x -> x.getPuntiConsumatore() == valoreMassimo)
                                                    .findAny().get();    //seleziono l'adesione...
                int finalResto = resto;
                listaAdesioni = listaAdesioni.stream()
                                .peek(x->{if(x.equals(adesioneDaModificare))
                                                x.setPuntiConsumatore(valoreMassimo - finalResto);}).toList();    //...la modifico...
                return listaAdesioni;   //... e restituisco la lista modificata
            }
            else   //se non vi è una singola adesione che più prendersi carico del resto, allora esso verrà pagato solo in parte per poi ricominciare il metodo
            {
                Adesione adesioneDaModificare = listaAdesioni.stream().filter(x -> x.getPuntiConsumatore() == valoreMassimo)
                                                .findAny().get();
                resto = resto - adesioneDaModificare.getPuntiConsumatore(); //parte del resto è stata pagata
                listaAdesioni = listaAdesioni.stream()
                                .peek(x->{if(x.equals(adesioneDaModificare))
                                                x.setPuntiConsumatore(0);}).toList();    //l'adesione finisce il credito
                return getListaAdesioniConSottrazioneDistribuita(resto,listaAdesioni); //il costo è diminuito al di sotto del valore cardinalitaValoriMaggioriDiZero; il prossimo ciclo è l'ultimo
            }
        }
        else    //vi è almeno un'adesione che andrebbe in negativo con la sottrazione
        {
            listaAdesioni = listaAdesioni.stream()
                            .peek(x->{if(x.getPuntiConsumatore()>0) //seleziono tutte le adesioni con punteggio maggiore di zero (quindi >= valoreMinimo)...
                                        x.setPuntiConsumatore(x.getPuntiConsumatore()-valoreMinimo);}).toList();  //... ed eseguo la sottrazione del valore minimo su ognuno di loro
            costo -= valoreMinimo * cardinalitaValoriMaggioriDiZero; //"n" adesioni hanno perso "x" punti, quindi il costo deve perdere "n*x" punti (avrà comunque un valore maggiore di cardinalitaValoriMaggioriDiZero)
            return getListaAdesioniConSottrazioneDistribuita(costo, listaAdesioni); //il costo è diminuito, si esegue un altro ciclo per ridurlo ancora
        }
    }

    private int getCardinalitaValoriMaggioriDiZero(List<Adesione> listaAdesioni) {
       return (int)listaAdesioni.stream().filter(x->x.getPuntiConsumatore()>0).count();
    }
    private int getValoreMinimo(List<Adesione> listaAdesioni) {
        int valoreMinimo = Integer.MAX_VALUE;
        for (Adesione a: listaAdesioni) { //estraggo il valore minimo
            if(a.getPuntiConsumatore() < valoreMinimo)
            {
                if(a.getPuntiConsumatore()==0)  //le adesioni con zero punti sono escluse
                    continue;
                valoreMinimo = a.getPuntiConsumatore();
            }
        }
        return valoreMinimo;
    }
    private int getValoreMassimo(List<Adesione> listaAdesioni) {
        int valoreMassimo = 0;
        for (Adesione a: listaAdesioni) { //estraggo il valore minimo
            if(a.getPuntiConsumatore() > valoreMassimo)
                valoreMassimo = a.getPuntiConsumatore();
        }
        return valoreMassimo;
    }
}