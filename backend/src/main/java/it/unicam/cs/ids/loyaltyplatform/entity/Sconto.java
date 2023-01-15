package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKSconto;

@Entity
@Table(name = "Sconti")
@IdClass(PKSconto.class)
public class Sconto {
    @Id
    private final Integer qualeAzienda;
    @Id
    private final Integer qualeProdotto;
    @Column(nullable = false)
    private Float sconto;
    @Column(nullable = false)
    private Boolean esclusivoVip;
    @ManyToOne
    @JoinColumn(name = "qualeAzienda", referencedColumnName = "idAzienda")
    private Azienda azienda;
    @ManyToOne
    @JoinColumn(name = "qualeProdotto", referencedColumnName = "idProdotto")
    private Prodotto prodotto;

    public Sconto() {
        qualeAzienda = 0;
        qualeProdotto = 0;
    }

    public Sconto(Integer qualeAzienda, Integer qualeProdotto, float sconto, boolean esclusivoVip) {
        this.qualeAzienda = qualeAzienda;
        this.qualeProdotto = qualeProdotto;
        this.sconto = sconto;
        this.esclusivoVip = esclusivoVip;
    }

    public Integer getQualeAzienda() {
        return qualeAzienda;
    }

    public Integer getQualeProdotto() {
        return qualeProdotto;
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
