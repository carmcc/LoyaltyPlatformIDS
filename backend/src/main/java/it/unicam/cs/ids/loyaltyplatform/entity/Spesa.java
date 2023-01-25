package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKSpesa;
import lombok.*;

@Entity
@Table(name = "Spese")
@IdClass(PKSpesa.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Spesa {
    @Id
    private Integer qualePagamento;
    @Id
    private Integer qualeProdotto;
    @Column(nullable = false)
    private Integer quantita;

}
