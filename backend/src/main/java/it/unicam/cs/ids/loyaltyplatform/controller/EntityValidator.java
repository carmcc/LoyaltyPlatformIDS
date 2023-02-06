package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.*;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityEmail;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityPassword;

import java.util.regex.Pattern;

public abstract class EntityValidator {
    /**
     * Controlla che l'entità passata sia valida
     * @param entity l'entità da controllare
     */
    public void validateEntity(Object entity) {
        if(entity instanceof AccountAziendale accountAziendale)
            validateAccountAziendale(accountAziendale);
        if(entity instanceof Adesione adesione)
            validateAdesione(adesione);
        if(entity instanceof Azienda azienda)
            validateAzienda(azienda);
        if(entity instanceof BuonoSconto buonoSconto)
            validateBuonoSconto(buonoSconto);
        if(entity instanceof Coalizione coalizione)
                validateCoalizione(coalizione);
        if(entity instanceof Consumatore consumatore)
                validateConsumatore(consumatore);
        if(entity instanceof Pagamento pagamento)
                validatePagamento(pagamento);
        if(entity instanceof Permesso permesso)
                validatePermesso(permesso);
        if(entity instanceof Premio premio)
                validatePremio(premio);
        if(entity instanceof Prodotto prodotto)
                validateProdotto(prodotto);
        if(entity instanceof Recensione recensione)
                validateRecensione(recensione);
        if(entity instanceof Ruolo ruolo)
                validateRuolo(ruolo);
        if(entity instanceof Sconto sconto)
                validateSconto(sconto);
        if(entity instanceof Sede sede)
                validateSede(sede);
        if(entity instanceof Spesa spesa)
                validateSpesa(spesa);
    }

    private void validateAccountAziendale(AccountAziendale accountAziendale) {
        if (accountAziendale == null)
            throw new NullPointerException("accountAziendale è vuoto");
        if (accountAziendale.getQualeAzienda() == null || accountAziendale.getQualeAzienda() < 0)
            throw new IllegalArgumentException("qualeAzienda non valido");
    }
    private void validateAdesione(Adesione adesione) {
        if(adesione == null)
            throw new NullPointerException("L'adesione passata è nulla");
        if ((adesione.getEsperienzaConsumatore() < 0) || (adesione.getLivelloConsumatore() <= 0) || (adesione.getPuntiConsumatore() < 0)
                || (adesione.getSalvadanaio() < 0 || adesione.getIsVip()))
            throw new IllegalArgumentException("Parametri non validi nell'adesione");

        if(adesione.getQualeAzienda() == null || adesione.getQualeAzienda()<=0
                || adesione.getQualeConsumatore() == null || adesione.getQualeConsumatore()<=0)
            throw new IllegalArgumentException("ID dell'adesione non validi");
    }

    private void validateAzienda(Azienda azienda) {
        if(azienda == null)
            throw new NullPointerException("L'azienda passata è nulla");
        if(azienda.getNomeAzienda().length() < 3)
            throw new IllegalArgumentException("Nome dell' azienda troppo corto");
        if(azienda.getDivisoreCashback() <= 0)
            throw new IllegalArgumentException("Il divisore cashback è negativo");
        if(azienda.getMoltiplicatoreVip() < 1)
            throw new IllegalArgumentException("Moltiplicatore VIP minore di 1");
        if(azienda.getQualeCoalizione() <= 0)
            throw new IllegalArgumentException("Coalizione non può essere minore di 0");
        checkEmail(azienda);
        checkIVA(azienda);
        controlloMoltSistemaLivelli(azienda);
        checkPassword(azienda);
    }

    private void checkNickname(Consumatore consumatore) {
        String NICKNAME_PATTERN = "^[a-zA-Z0-9_-]+$";
        String nickname = consumatore.getNickname();
        if(!Pattern.compile(NICKNAME_PATTERN).matcher(nickname).find())
            throw new IllegalArgumentException("Il nickname non è valido, può contenere solo lettere, numeri, underscore e trattini");
    }

    private void checkEmail(EntityEmail entity) {
        String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        String email = entity.getEmail();
        if(!Pattern.compile(EMAIL_PATTERN).matcher(email).find())
            throw new IllegalArgumentException("L'email non è valida, deve essere nel formato: example@gmail.com");
    }

    private void checkIVA(Azienda azienda) {
        String IVA_PATTERN = "^IT[0-9]{11}$";
        String iva = azienda.getIVA();
        if(!Pattern.compile(IVA_PATTERN).matcher(iva).find())
            throw new IllegalArgumentException("La partita IVA non è valida, deve cominciare con IT e avere 11 cifre");
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
        EntityValidator.State state = EntityValidator.State.COMMA;
        short puntiNelNumero = 0;
        short virgoleIndividuate = 0;
        int lunghezza = stringa.length;
        for (char c : stringa) {
            switch (state) {
                case COMMA -> {
                    if (c == '[')
                    {state = EntityValidator.State.OPENSQUARE; break;}
                    throw new IllegalArgumentException("Formato del Moltiplicatore Sistema a Livelli non valido");
                }
                case NUMBER -> {
                    if (c == '.' && puntiNelNumero == 0)
                    {state = EntityValidator.State.DOT; puntiNelNumero++; break;}
                    if (c == ']')
                    {state = EntityValidator.State.CLOSESQUARE; puntiNelNumero = 0; break;}
                    throw new IllegalArgumentException("Formato del Moltiplicatore Sistema a Livelli non valido");
                }
                case DOT, OPENSQUARE -> {
                    if (testNumber(c))
                    {state = EntityValidator.State.NUMBER; break;}
                    throw new IllegalArgumentException("Formato del Moltiplicatore Sistema a Livelli non valido");
                }
                case CLOSESQUARE -> {
                    if (c == ',' && virgoleIndividuate < 4)
                    {state = EntityValidator.State.COMMA; virgoleIndividuate++; break;}
                    throw new IllegalArgumentException("Formato del Moltiplicatore Sistema a Livelli non valido");
                }
            }
        }
        if (stringa[lunghezza - 1] != ']')
        {throw new IllegalArgumentException("Formato del Moltiplicatore Sistema a Livelli non valido");}
    }

    /**
     * Questo metodo controlla se il carattere passato è un numero compreso tra 0 e 9.
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
     * @param entity    L'oggetto da cui estrarre "password"
     */
    private void checkPassword(EntityPassword entity) {
        String minuscola = "[a-z]";
        String maiuscola = "[A-Z]";
        String specialiONumeri = "[\\W|\\d]";
        short lunghezzaMin = 8;
        String password = entity.getPassword();

        if(!Pattern.compile(minuscola).matcher(password).find())
            throw new IllegalArgumentException("La password deve contenere almeno una lettera minuscola");
        if(!Pattern.compile(maiuscola).matcher(password).find())
            throw new IllegalArgumentException("La password deve contenere almeno una lettera maiuscola");
        if(!Pattern.compile(specialiONumeri).matcher(password).find())
            throw new IllegalArgumentException("La password deve contenere almeno un carattere speciale o un numero");
        if(password.length() < lunghezzaMin)
            throw new IllegalArgumentException("La password deve contenere almeno 8 caratteri");
    }

    private void validateBuonoSconto(BuonoSconto buonoSconto) {
        if (buonoSconto == null)
            throw new NullPointerException("buono sconto è nullo");
        if(buonoSconto.getDataCreazione() == null || buonoSconto.getDataScadenza() == null)
            throw new NullPointerException("Almeno una delle due date assente nel buono sconto");
        if(buonoSconto.getValore() <= 0)
            throw new IllegalArgumentException("Valore del buono sconto non valido");
        if(buonoSconto.getQualeAzienda() == null || buonoSconto.getQualeAzienda() <= 0
                || buonoSconto.getQualeConsumatore() == null || buonoSconto.getQualeConsumatore() <= 0)
            throw new IllegalArgumentException("Id azienda o consumatore non validi");
    }
    private void validateCoalizione(Coalizione coalizione) {
        if(coalizione == null)
            throw new NullPointerException("coalizione è nulla");
        if(coalizione.getParametroBuoniSpesa() <= 0)
            throw new IllegalArgumentException("parametro buoni spesa negativo o uguale a zero");
        if (coalizione.getPenalitaCondivisione() < 1)
            throw new IllegalArgumentException("penalità condivisione minore di 1");
        if(coalizione.getPercentualeRitiroPremi() < 0 || coalizione.getPercentualeRitiroPremi() >= 100)
            throw new IllegalArgumentException("percentuale ritiro premi non valida");

    }

    private void validateConsumatore(Consumatore consumatore) {
        if(consumatore == null)
            throw new NullPointerException("Consumatore è nullo");
        checkNickname(consumatore);
        checkEmail(consumatore);
        checkPassword(consumatore);
    }

    private void validatePagamento(Pagamento pagamento) {
        if(pagamento == null)
            throw new IllegalArgumentException("pagamento è nullo");
        if(pagamento.getDataPagamento() == null)
            throw new IllegalArgumentException("data pagamento assente");
        if(pagamento.getCostoTotale() < 0)
            throw new IllegalArgumentException("costo minore di zero");
        if(pagamento.getQualeConsumatore() == null || pagamento.getQualeConsumatore() <= 0)
            throw new IllegalArgumentException("Id consumatore non valido");
    }
    private void validatePermesso(Permesso permesso) {
        if(permesso == null)
            throw new NullPointerException("il record da inserire non può essere nullo");
    }

    private void validatePremio(Premio premio) {
        if(premio == null)
            throw new NullPointerException("premio è nullo");
        if(premio.getCosto() < 0 || premio.getQualeAzienda() <= 0 || premio.getQualeProdotto() <= 0)
            throw new IllegalArgumentException("parametri non validi");
    }
    private void validateProdotto(Prodotto prodotto) {
        if(prodotto == null)
            throw new NullPointerException("prodotto è nullo");
        if(prodotto.getNome() == null)
            throw new NullPointerException("nome prodotto è nullo");
        if(prodotto.getNome().isBlank())
            throw new IllegalArgumentException("nome prodotto è vuoto");
    }
    private void validateRecensione(Recensione recensione) {
        if(recensione == null)
            throw new NullPointerException("recensione è nullo");
        Integer valutazione = recensione.getValutazione();
        if(valutazione == null || valutazione < 1 || valutazione > 5)
            throw new IllegalArgumentException("valore valutazione non compreso tra 1 e 5");
        if(recensione.getQualeConsumatore() <= 0 || recensione.getQualeAzienda() <= 0)
            throw new IllegalArgumentException("id consumatore o azienda non valido");
    }

    private void validateRuolo(Ruolo ruolo)
    {
        if(ruolo == null)
            throw new NullPointerException("ruolo è nullo");
        if(ruolo.getNome().length() < 2)
            throw new IllegalArgumentException("nome non valido");
        if(ruolo.getQualeAzienda() <= 0 || ruolo.getQualeSeriale() <= 0 || ruolo.getQualePermesso().isBlank())
            throw new IllegalArgumentException("Parametri non validi");
    }

    private void validateSconto(Sconto sconto)
    {
        if(sconto == null)
            throw new NullPointerException("Lo sconto inserito è nullo");
        if(sconto.getQualeAzienda() <= 0 || sconto.getQualeProdotto() <= 0 || sconto.getSconto() <= 0)
            throw new IllegalArgumentException("Parametri non validi per lo sconto");
    }

    private void validateSede(Sede sede)
    {
        if(sede == null)
            throw new NullPointerException("La sede inserita è nulla");
        if (sede.getIdSede() <= 0 || sede.getQualeAzienda() == null || sede.getQualeAzienda() <= 0)
            throw new IllegalArgumentException("Parametri non validi per la sede");
        if(sede.getVia() == null || sede.getVia().isEmpty()
                || sede.getCap() == null || sede.getCap().isEmpty()
                || sede.getCitta() == null || sede.getCitta().isEmpty()
                || sede.getRegione() == null || sede.getRegione().isEmpty()
                || sede.getCivico() == null || sede.getCivico().isEmpty())
            throw new IllegalArgumentException("Indirizzo non valido per la sede");
    }

    private void validateSpesa(Spesa spesa)
    {
        if(spesa == null)
            throw new NullPointerException("spesa è nulla");
        if(spesa.getQuantita() == null || spesa.getQuantita() <= 0)
            throw new IllegalArgumentException("quantità spesa non valida");
        if(spesa.getQualePagamento() == null || spesa.getQualePagamento() <= 0)
            throw new IllegalArgumentException("id pagamento non valido");
        if(spesa.getQualeProdotto() == null || spesa.getQualeProdotto() <= 0)
            throw new IllegalArgumentException("id prodotto non valido");
    }

}
