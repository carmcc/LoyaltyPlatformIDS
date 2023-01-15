package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table (name = "BuoniSconto")
public class BuonoSconto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer idBuono;
    @Column(nullable = false)
    private Integer qualeConsumatore;
    @Column(nullable = false)
    private Integer qualeAzienda;
    @Column(nullable = false)
    private Date dataCreazione;
    @Column(nullable = false)
    private Date dataScadenza;
    @Column(nullable = false)
    private Float valore;
    @ManyToOne(targetEntity = Azienda.class)
    @JoinColumn(name = "qualeAzienda", referencedColumnName = "idAzienda")
    private Azienda azienda;
    @ManyToOne(targetEntity = Consumatore.class)
    @JoinColumn(name = "qualeConsumatore", referencedColumnName = "idConsumatore")
    private Consumatore consumatore;

    public BuonoSconto() {
        idBuono = 0;
    }
    public BuonoSconto(Integer idBuono, Integer qualeConsumatore, Integer qualeAzienda, Date dataCreazione, Date dataScadenza, float valore) {
        this.idBuono = idBuono;
        this.qualeConsumatore = qualeConsumatore;
        this.qualeAzienda = qualeAzienda;
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.valore = valore;
    }

    public Integer getIdBuono() {
        return idBuono;
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
