package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKAccountAziendale;
import lombok.*;


@Entity
@Table(name = "AccountAziendali")
@IdClass(PKAccountAziendale.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
public class AccountAziendale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//TODO com il postaman non funge, funziona solo se tolgo autoincrement
    private Integer seriale;
    @Id
    private Integer qualeAzienda;
}
