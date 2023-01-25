package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKSconto;
import lombok.*;

@Entity
@Table(name = "Sconti")
@IdClass(PKSconto.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Sconto {
    @Id
    private Integer qualeAzienda;
    @Id
    private Integer qualeProdotto;
    @Column(nullable = false)
    private Float sconto;
    @Column(nullable = false)
    private Boolean esclusivoVip;
}
