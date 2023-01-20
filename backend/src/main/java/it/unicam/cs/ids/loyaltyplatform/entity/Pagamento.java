package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Pagamenti")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer idPagamento;
    @Column(nullable = false)
    private Integer qualeConsumatore;
    @Column(nullable = false)
    private Date dataPagamento;
    @Column(nullable = false)
    private Float costoTotale;

    public Pagamento() {
        idPagamento = 0;
    }

    public Pagamento(Integer idPagamento, Integer qualeConsumatore, Date dataPagamento, float costoTotale) {
        this.idPagamento = idPagamento;
        this.qualeConsumatore = qualeConsumatore;
        this.dataPagamento = dataPagamento;
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

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date data_pagamento) {
        this.dataPagamento = data_pagamento;
    }

    public float getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(float costoTotale) {
        this.costoTotale = costoTotale;
    }
}
