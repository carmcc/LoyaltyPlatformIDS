package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
@Entity
@Table (name = "BuoniSconto")
public class BuonoSconto{
    @Id
    private Integer idBuoni;
    private Integer qualeConsumatore;
    private Integer qualeAzienda;
    private Date dataCreazione;
    private Date dataScadenza;
    private float valore;

    public BuonoSconto()
    {

    }
    public BuonoSconto(Integer idBuoni, Integer qualeConsumatore, Integer qualeAzienda, Date dataCreazione, Date dataScadenza, float valore) {
        this.idBuoni = idBuoni;
        this.qualeConsumatore = qualeConsumatore;
        this.qualeAzienda = qualeAzienda;
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.valore = valore;
    }

    public Integer getIdBuoni() {
        return idBuoni;
    }

    public void setIdBuoni(Integer idBuoni) {
        this.idBuoni = idBuoni;
    }

    public Integer getQualeConsumatore() {
        return qualeConsumatore;
    }

    public void setQualeConsumatore(Integer qualeConsumatore) {
        this.qualeConsumatore = qualeConsumatore;
    }

    public Integer getQualeAzienda() {
        return qualeAzienda;
    }

    public void setQualeAzienda(Integer qualeAzienda) {
        this.qualeAzienda = qualeAzienda;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public float getValore() {
        return valore;
    }

    public void setValore(float valore) {
        this.valore = valore;
    }
}
