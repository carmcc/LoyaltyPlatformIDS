package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKAccountAziendale;
import lombok.*;
@Entity
@Table(name = "ACCOUNT_AZIENDALI")
@IdClass(PKAccountAziendale.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class AccountAziendale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCOUNT_AZIENDALI_SEQ")
    @SequenceGenerator(name = "ACCOUNT_AZIENDALI_SEQ", sequenceName = "ACCOUNT_AZIENDALI_SEQ", allocationSize = 1)
    private Integer seriale;
    @Id
    private Integer qualeAzienda;

}
