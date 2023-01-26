package it.unicam.cs.ids.loyaltyplatform.controller;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if(azienda.getNomeAzienda().length() < 3 || !passwordValida(azienda) ||
           !moltSistemaLivelliValido(azienda) || azienda.getDivisoreCashback() <= 0 || azienda.getMoltiplicatoreVip() < 1)
        {throw new IllegalArgumentException("parametri non validi in azienda");}
    }

    private enum State {
        OPENSQUARE,CLOSESQUARE,COMMA,DOT,NUMBER
    }

    /**
     * Questo metodo controlla che la stringa inserita rispetto il formato "[X],[X]...",
     *  come illustrato dalle seguenti regole:
     *      -"X" indica "n" oppure "n.m", dove "n" è un numero tra 1 e 9 ed "m" è un numero tra 0 e 9.
     *      -Le quadre devono essere chiuse contenendo solamente X.
     *      -ì]" deve essere l'ultimo carattere.
     *      -Devono esserci da 1 a 5 "[X]" separati da ",".
     *  Quindi i valori dentro le quadre possono andare da 1 a 9.9.
     *
     * @param azienda   Oggetto da cui estrarre la stringa "moltSistemaLivelli"
     * @return  True se la stringa rispetta il linguaggio. False altrimenti.
     */
    private boolean moltSistemaLivelliValido(Azienda azienda) {     //TODO discutere sulla dimensione dei numeri e sulla quantità massima di valori
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
                    return false;
                }
                case NUMBER -> {
                    if (c == '.' && puntiNelNumero == 0)
                        {state = State.DOT; puntiNelNumero++; break;}
                    if (c == ']')
                        {state = State.CLOSESQUARE; puntiNelNumero = 0; break;}
                    return false;
                }
                case DOT -> {
                    if (testNumber(c) || c == '0')
                        {state = State.NUMBER; break;}
                    return false;
                }
                case OPENSQUARE -> {
                    if (testNumber(c))
                        {state = State.NUMBER; break;}
                    return false;
                }
                case CLOSESQUARE -> {
                    if (c == ',' && virgoleIndividuate < 4)
                    {state = State.COMMA; virgoleIndividuate++; break;}
                    return false;
                }
            }
        }
        return (stringa[lunghezza - 1] == ']');
    }

    /**
     * Questo metodo controlla se il carattere passato è un numero compreso tra 1 e 9.
     *
     * @param c carattere da controllare
     * @return True o false
     */
    private boolean testNumber(char c) {
        char[] number = {'0','1','2','3','4','5','6','7','8','9'};
        for (int i=1; i<=9; i++) {
            if (c == number[i]) {return true;}
        }
        return false;
    }

    /**
     * Questo metodo controlla se la password rispetta le regole di linguaggio
     *
     * @param azienda   L'oggetto da cui estrarre "password"
     * @return  True se la password è abbastanza sicura, False alrimenti.
     */
    private boolean passwordValida(Azienda azienda) {   //TODO decidere regola password
        return (azienda.getPassword().length() >= 5);
    }
}
