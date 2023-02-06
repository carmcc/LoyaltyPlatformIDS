package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKPremio;
import lombok.*;
@Entity
@Table(name = "PREMI")
@IdClass(PKPremio.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Premio {
    @Id
    private Integer qualeAzienda;
    @Id
    private Integer qualeProdotto;
    @Column(nullable = false)
    private Integer costo;
}
