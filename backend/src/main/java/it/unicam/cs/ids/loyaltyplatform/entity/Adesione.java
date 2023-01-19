package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKAdesione;

import java.util.Date;

@Entity
@Table(name = "Adesioni")
@IdClass(PKAdesione.class)
public class Adesione {
    @Id
    private final Integer qualeAzienda;
    @Id
    private final Integer qualeConsumatore;
    @Column(nullable = false)
    private Integer esperienzaConsumatore;
    @Column(nullable = false)
    private Boolean isVip;
    @Column(nullable = false)
    private Integer livelloConsumatore;
    @Column(nullable = false)
    private Integer puntiConsumatore;
    @Column(nullable = false)
    private Float salvadanaio;
    private Date ultimaSpesa;

    public Adesione() {
        qualeAzienda = 0;
        qualeConsumatore = 0;
    }

    public Adesione(Integer qualeAzienda, Integer qualeConsumatore, Integer esperienzaConsumatore, boolean isVip, Integer livelloConsumatore, Integer puntiConsumatore, float salvadanaio, Date ultimaSpesa) {
        this.qualeAzienda = qualeAzienda;
        this.qualeConsumatore = qualeConsumatore;
        this.esperienzaConsumatore = esperienzaConsumatore;
        this.isVip = isVip;
        this.livelloConsumatore = livelloConsumatore;
        this.puntiConsumatore = puntiConsumatore;
        this.salvadanaio = salvadanaio;
        this.ultimaSpesa = ultimaSpesa;
    }

    //getters and setters
    public Integer getQualeAzienda() {
        return qualeAzienda;
    }

    public Integer getQualeConsumatore() {
        return qualeConsumatore;
    }

    public Integer getEsperienzaConsumatore() {
        return esperienzaConsumatore;
    }

    public void setEsperienzaConsumatore(Integer esperienzaConsumatore) {
        this.esperienzaConsumatore = esperienzaConsumatore;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public Integer getLivelloConsumatore() {
        return livelloConsumatore;
    }

    public void setLivelloConsumatore(Integer livelloConsumatore) {
        this.livelloConsumatore = livelloConsumatore;
    }

    public Integer getPuntiConsumatore() {
        return puntiConsumatore;
    }

    public void setPuntiConsumatore(Integer puntiConsumatore) {
        this.puntiConsumatore = puntiConsumatore;
    }

    public float getSalvadanaio() {
        return salvadanaio;
    }

    public void setSalvadanaio(float salvadanaio) {
        this.salvadanaio = salvadanaio;
    }

    public Date getUltimaSpesa() {
        return ultimaSpesa;
    }

    public void setUltimaSpesa(Date ultimaSpesa) {
        this.ultimaSpesa = ultimaSpesa;
    }
}
