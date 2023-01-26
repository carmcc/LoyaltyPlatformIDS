package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
@Entity
@Table (name = "BuoniSconto")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class BuonoSconto {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer qualeConsumatore;
    @Column(nullable = false)
    private Integer qualeAzienda;
    @Column(nullable = false)
    private Date dataCreazione;
    @Column(nullable = false)
    private Date dataScadenza;
    @Column(nullable = false)
    private Float valore;
}
