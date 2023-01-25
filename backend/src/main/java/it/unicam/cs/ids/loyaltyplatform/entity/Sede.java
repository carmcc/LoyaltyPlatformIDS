package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Sedi")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
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

    public Sede(Integer idSede, Integer qualeAzienda, String via, String cap, String regione, String citta, String civico) {
        this.idSede = idSede;
        this.qualeAzienda = qualeAzienda;
        this.via = via;
        this.cap = cap;
        this.regione = regione;
        this.citta = citta;
        this.civico = civico;
    }

    public Sede() {}
}
