package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Table (name = "BuoniSconto")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class BuonoSconto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBuono;
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

    public BuonoSconto(Integer idBuono, Integer qualeConsumatore, Integer qualeAzienda, Date dataCreazione, Date dataScadenza, float valore) {
        this.idBuono = idBuono;
        this.qualeConsumatore = qualeConsumatore;
        this.qualeAzienda = qualeAzienda;
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.valore = valore;
    }

    public BuonoSconto() {}
}
