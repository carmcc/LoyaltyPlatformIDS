package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKSpesa;

@Entity
@Table(name = "Spese")
@IdClass(PKSpesa.class)
public class Spesa {
    @Id
    private final Integer qualePagamento;
    @Id
    private final Integer qualeProdotto;
    @Column(nullable = false)
    private Integer quantita;
    @ManyToOne
    @JoinColumn(name = "qualePagamento", referencedColumnName = "idPagamento")
    private Pagamento pagamento;
    @ManyToOne
    @JoinColumn(name = "qualeProdotto", referencedColumnName = "idProdotto")
    private Prodotto prodotto;

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
