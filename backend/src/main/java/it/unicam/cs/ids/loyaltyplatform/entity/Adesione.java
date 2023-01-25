package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKAdesione;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Adesioni")
@IdClass(PKAdesione.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Adesione {
    @Id
    private Integer qualeAzienda;
    @Id
    private Integer qualeConsumatore;
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

    public Adesione() {}
}
