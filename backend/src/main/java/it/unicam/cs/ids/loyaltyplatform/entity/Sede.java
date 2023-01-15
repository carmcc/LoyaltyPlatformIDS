package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Sedi")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSede;
    @Column(nullable = false)
    private Integer qualeAzienda;
    @Column(nullable = false)
    private String via;
    @Column(nullable = false)
    private String cap;
    @Column(nullable = false)
    private String regione;
    @Column(nullable = false)
    private String citta;
    @Column(nullable = false)
    private String civico;
    @ManyToOne
    @JoinColumn(name = "qualeAzienda", referencedColumnName = "idAzienda")
    private Azienda azienda;

    public Sede() {
        this.idSede = 0;
    }

    public Sede(Integer idSede, Integer qualeAzienda, String via, String cap, String regione, String citta, String civico) {
        this.idSede = idSede;
        this.qualeAzienda = qualeAzienda;
        this.via = via;
        this.cap = cap;
        this.regione = regione;
        this.citta = citta;
        this.civico = civico;
    }

    public Integer getQualeAzienda() {
        return qualeAzienda;
    }

    public void setQualeAzienda(Integer qualeAzienda) {
        this.qualeAzienda = qualeAzienda;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }
}
