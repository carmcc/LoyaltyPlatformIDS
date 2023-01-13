package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKSconto;

@Entity
@Table(name = "Sconti")
@IdClass(PKSconto.class)
public class Sconto {       //TODO aggiungi foreight keys
    @Id
    private final Integer quale_azienda;
    @Id
    private final Integer quale_prodotto;
    @Column(nullable = false)
    private float sconto;
    @Column(nullable = false)
    private boolean esclusivoVip;

    public Sconto() {
        quale_azienda = 0;
        quale_prodotto = 0;
    }

    public Sconto(Integer quale_azienda, Integer quale_prodotto, float sconto, boolean esclusivoVip) {
        this.quale_azienda = quale_azienda;
        this.quale_prodotto = quale_prodotto;
        this.sconto = sconto;
        this.esclusivoVip = esclusivoVip;
    }

    public Integer getQuale_azienda() {
        return quale_azienda;
    }

    public Integer getQuale_prodotto() {
        return quale_prodotto;
    }

    public float getSconto() {
        return sconto;
    }

    public void setSconto(float sconto) {
        this.sconto = sconto;
    }

    public boolean isEsclusivoVip() {
        return esclusivoVip;
    }

    public void setEsclusivoVip(boolean esclusivoVip) {
        this.esclusivoVip = esclusivoVip;
    }
}
