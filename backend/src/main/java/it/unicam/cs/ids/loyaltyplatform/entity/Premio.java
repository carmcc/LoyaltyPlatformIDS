package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKPremio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Premi")
@IdClass(PKPremio.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Premio {
    @Id
    private Integer qualeAzienda;
    @Id
    private Integer qualeProdotto;
    @Column(nullable = false)
    private Integer costo;

    public Premio(Integer qualeAzienda, Integer qualeProdotto, Integer costo) {
        this.qualeAzienda = qualeAzienda;
        this.qualeProdotto = qualeProdotto;
        this.costo = costo;
    }

    public Premio() {}
}
