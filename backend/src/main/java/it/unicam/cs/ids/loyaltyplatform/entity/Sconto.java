package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKSconto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Sconti")
@IdClass(PKSconto.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Sconto {
    @Id
    private Integer qualeAzienda;
    @Id
    private Integer qualeProdotto;
    @Column(nullable = false)
    private Float sconto;
    @Column(nullable = false)
    private Boolean esclusivoVip;

    public Sconto(Integer qualeAzienda, Integer qualeProdotto, float sconto, boolean esclusivoVip) {
        this.qualeAzienda = qualeAzienda;
        this.qualeProdotto = qualeProdotto;
        this.sconto = sconto;
        this.esclusivoVip = esclusivoVip;
    }

    public Sconto() {}

}
