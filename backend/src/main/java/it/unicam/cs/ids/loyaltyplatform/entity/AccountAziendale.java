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
@AllArgsConstructor
public class AccountAziendale {
    @Id
    @GeneratedValue
    private Integer seriale;
    @Id
    private Integer qualeAzienda;

}
