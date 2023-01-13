package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Date;

@Entity
@Table(name = "Pagamenti")
public class Pagamento {        //TODO aggiungere foreight keys
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer idPagamento;
    @Column(nullable = false)
    private Integer qualeConsumatore;
    @Column(nullable = false)
    private Date data_pagamento;
    @Column(nullable = false)
    private float costoTotale;

    public Pagamento() {
        idPagamento = 0;
    }

    public Pagamento(Integer idPagamento, Integer qualeConsumatore, Date data_pagamento, float costoTotale) {
        this.idPagamento = idPagamento;
        this.qualeConsumatore = qualeConsumatore;
        this.data_pagamento = data_pagamento;
        this.costoTotale = costoTotale;
    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public Integer getQualeConsumatore() {
        return qualeConsumatore;
    }

    public void setQualeConsumatore(Integer qualeConsumatore) {
        this.qualeConsumatore = qualeConsumatore;
    }

    public Date getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public float getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(float costoTotale) {
        this.costoTotale = costoTotale;
    }
}
