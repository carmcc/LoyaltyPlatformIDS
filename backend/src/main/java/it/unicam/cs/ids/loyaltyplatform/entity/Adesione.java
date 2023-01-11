package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "Adesioni")
public class Adesione
{
    @Id
    //TODO settare le chiavi composte
    private Integer qualeAzienda;
    private Integer qualeConsumatore;

    private Integer esperienzaConsumatore;
    private boolean isVip;

    private Integer livelloConsumatore;

    private Integer puntiConsumatore;
    private float salvadanaio;
    private Date ultimaSpesa;

    public Adesione() {
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

    public void setQualeAzienda(Integer qualeAzienda) {
        this.qualeAzienda = qualeAzienda;
    }

    public Integer getQualeConsumatore() {
        return qualeConsumatore;
    }

    public void setQualeConsumatore(Integer qualeConsumatore) {
        this.qualeConsumatore = qualeConsumatore;
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
