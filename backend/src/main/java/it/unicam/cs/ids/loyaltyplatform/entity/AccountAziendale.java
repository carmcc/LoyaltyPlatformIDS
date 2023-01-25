package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKAccountAziendale;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "AccountAziendali")
@IdClass(PKAccountAziendale.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class AccountAziendale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seriale;
    @Id
    private Integer qualeAzienda;


    public AccountAziendale(Integer seriale, Integer qualeAzienda) {
        this.seriale = seriale;
        this.qualeAzienda = qualeAzienda;
    }

    public AccountAziendale() {}
}
