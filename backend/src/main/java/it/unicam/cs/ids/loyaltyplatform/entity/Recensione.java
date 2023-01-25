package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKRecensione;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Recensioni")
@IdClass(PKRecensione.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Recensione {

    @Id
    private Integer qualeConsumatore;
    @Id
    private Integer qualeAzienda;
    @Column(nullable = false)
    private Integer valutazione;
    private String commento;


    public Recensione(Integer qualeConsumatore, Integer qualeAzienda, Integer valutazione, String commento) {
        this.qualeConsumatore = qualeConsumatore;
        this.qualeAzienda = qualeAzienda;
        this.valutazione = valutazione;
        this.commento = commento;
    }

    public Recensione() {}
}
