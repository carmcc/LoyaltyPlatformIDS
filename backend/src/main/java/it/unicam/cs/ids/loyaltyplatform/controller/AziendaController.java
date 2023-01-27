package it.unicam.cs.ids.loyaltyplatform.controller;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/azienda")
@AllArgsConstructor
public class AziendaController
{
    private final AziendaService aziendaService;

    @GetMapping(value = "/getAziendaById/{id}")
    public Azienda getAziendaById(@PathVariable("id") Integer id) {
        return this.aziendaService.getAziendaById(id);
    }

    @GetMapping(value = "/getAllAziende")
    public List<Azienda> getAllAziende() {
        return this.aziendaService.getAllAziende();
    }

    //crea un azienda con metodo POST
    @RequestMapping(value = "/addAzienda", method = RequestMethod.POST)
    public Azienda addAzienda(@RequestBody Azienda azienda) {
        controlloValiditaAzienda(azienda);
        if(getAziendaById(azienda.getIdAzienda()) != null)
        {throw new IllegalArgumentException("il record da aggiungere esiste già");}

        return this.aziendaService.addAzienda(azienda);
    }

    @DeleteMapping(value = "/deleteAziendaById/{id}")
    public void deleteAziendaById(@PathVariable("id") Integer id) {
        this.aziendaService.deleteAziendaById(id);
    }

    //aggiorna un azienda con metodo PUT
    @PutMapping(value = "/updateAzienda")
    public void updateAzienda(@RequestBody Azienda azienda) {
        controlloValiditaAzienda(azienda);
        if(getAziendaById(azienda.getIdAzienda()) == null)
        {throw new IllegalArgumentException("il record da modificare non esiste");}

        this.aziendaService.updateAzienda(azienda);
    }

    private void controlloValiditaAzienda(Azienda azienda) {    //TODO discutere se sono stati fatti tutti i controlli necesssari
        if(azienda == null) {throw new NullPointerException("azienda è nulla");}
        if(azienda.getNomeAzienda().length() < 3 || azienda.getDivisoreCashback() <= 0 ||
                azienda.getMoltiplicatoreVip() < 1)
        {throw new IllegalArgumentException("parametri non validi in azienda");}
        controlloMoltSistemaLivelli(azienda);
        controlloPassword(azienda);
    }

    private enum State {
        OPENSQUARE,CLOSESQUARE,COMMA,DOT,NUMBER
    }

    /**
     * Questo metodo controlla che la stringa inserita rispetto il formato "[X],[X]...",
     *  come illustrato dalle seguenti regole:
     *      -"X" indica "n" oppure "n.n", dove "n" è un numero tra 0 e 9
     *      -Le quadre devono essere chiuse contenendo solamente X.
     *      -"]" deve essere l'ultimo carattere.
     *      -Devono esserci da 1 a 5 "[X]" separati da ",".
     *  Quindi i valori dentro le quadre possono andare da 1 a 9.9.
     *
     * @param azienda   Oggetto da cui estrarre la stringa "moltSistemaLivelli"
     * @exception IllegalArgumentException se la stringa non rispetta il linguaggio
     */
    private void controlloMoltSistemaLivelli(Azienda azienda) {
        char[] stringa = azienda.getMoltSistemaLivelli().toCharArray();
        State state = State.COMMA;
        short puntiNelNumero = 0;
        short virgoleIndividuate = 0;
        int lunghezza = stringa.length;
        for (char c : stringa) {
            switch (state) {
                case COMMA -> {
                    if (c == '[')
                        {state = State.OPENSQUARE; break;}
                    throw new IllegalArgumentException("Formato del Moltiplicatore Sistema a Livelli non valido");
                }
                case NUMBER -> {
                    if (c == '.' && puntiNelNumero == 0)
                        {state = State.DOT; puntiNelNumero++; break;}
                    if (c == ']')
                        {state = State.CLOSESQUARE; puntiNelNumero = 0; break;}
                    throw new IllegalArgumentException("Formato del Moltiplicatore Sistema a Livelli non valido");
                }
                case DOT, OPENSQUARE -> {
                    if (testNumber(c))
                        {state = State.NUMBER; break;}
                    throw new IllegalArgumentException("Formato del Moltiplicatore Sistema a Livelli non valido");
                }
                case CLOSESQUARE -> {
                    if (c == ',' && virgoleIndividuate < 4)
                    {state = State.COMMA; virgoleIndividuate++; break;}
                    throw new IllegalArgumentException("Formato del Moltiplicatore Sistema a Livelli non valido");
                }
            }
        }
        if (stringa[lunghezza - 1] != ']')
        {throw new IllegalArgumentException("Formato del Moltiplicatore Sistema a Livelli non valido");}
    }

    /**
     * Questo metodo controlla se il carattere passato è un numero compreso tra 1 e 9.
     *
     * @param c carattere da controllare
     * @return True o false
     */
    private boolean testNumber(char c) {
        char[] number = {'0','1','2','3','4','5','6','7','8','9'};
        for (int i=0; i<=9; i++) {
            if (c == number[i]) {return true;}
        }
        return false;
    }

    /**
     * Questo metodo controlla se la password rispetta le regole di linguaggio
     *
     * @param azienda   L'oggetto da cui estrarre "password"
     */
    private void controlloPassword(Azienda azienda) {
        String minuscola = "[a-z]";
        String maiuscola = "[A-Z]";
        String specialiONumeri = "[\\W|\\d]";
        short lunghezzaMin = 8;
        String password = azienda.getPassword();

        if(!Pattern.compile(minuscola).matcher(password).find())
            {throw new IllegalArgumentException("La password deve contenere almeno una lettera minuscola");}
        if(!Pattern.compile(maiuscola).matcher(password).find())
            {throw new IllegalArgumentException("La password deve contenere almeno una lettera maiuscola");}
        if(!Pattern.compile(specialiONumeri).matcher(password).find())
            {throw new IllegalArgumentException("La password deve contenere almeno un carattere speciale o un numero");}
        if(password.length() < lunghezzaMin)
            {throw new IllegalArgumentException("La password deve contenere almeno 8 caratteri");}
    }
}
