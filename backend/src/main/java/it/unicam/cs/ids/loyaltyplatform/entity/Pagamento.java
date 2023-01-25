package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Pagamenti")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPagamento;
    @Column(nullable = false)
    private Integer qualeConsumatore;
    @Column(nullable = false)
    private Date dataPagamento;
    @Column(nullable = false)
    private Float costoTotale;

    public Pagamento(Integer idPagamento, Integer qualeConsumatore, Date dataPagamento, float costoTotale) {
        this.idPagamento = idPagamento;
        this.qualeConsumatore = qualeConsumatore;
        this.dataPagamento = dataPagamento;
        this.costoTotale = costoTotale;
    }

    public Pagamento() {}
}
