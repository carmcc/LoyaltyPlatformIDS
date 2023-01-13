package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKSpesa;

@Entity
@Table(name = "Spese")
@IdClass(PKSpesa.class)
public class Spesa {        //TODO aggiungere foreight keys
    @Id
    private final Integer qualePagamento;
    @Id
    private final Integer qualeProdotto;
    @Column(nullable = false)
    private Integer quantita;

    public Spesa() {
        qualePagamento = 0;
        qualeProdotto = 0;
    }

    public Spesa(Integer qualePagamento, Integer qualeProdotto, Integer quantita) {
        this.qualePagamento = qualePagamento;
        this.qualeProdotto = qualeProdotto;
        this.quantita = quantita;
    }

    public Integer getQualePagamento() {
        return qualePagamento;
    }

    public Integer getQualeProdotto() {
        return qualeProdotto;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }
}
