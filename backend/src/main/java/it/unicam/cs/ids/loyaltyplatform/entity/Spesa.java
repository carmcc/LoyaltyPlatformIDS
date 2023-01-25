package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKSpesa;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Spese")
@IdClass(PKSpesa.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Spesa {
    @Id
    private Integer qualePagamento;
    @Id
    private Integer qualeProdotto;
    @Column(nullable = false)
    private Integer quantita;


    public Spesa(Integer qualePagamento, Integer qualeProdotto, Integer quantita) {
        this.qualePagamento = qualePagamento;
        this.qualeProdotto = qualeProdotto;
        this.quantita = quantita;
    }

    public Spesa() {}
}
