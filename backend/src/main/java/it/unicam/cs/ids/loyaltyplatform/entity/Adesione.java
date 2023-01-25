package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKAdesione;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Adesioni")
@IdClass(PKAdesione.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
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
}
