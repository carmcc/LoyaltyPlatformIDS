package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
@Entity
@Table (name = "BUONI_SCONTO")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class BuonoSconto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "BUONI_SCONTO_SEQ")
    @SequenceGenerator(name = "BUONI_SCONTO_SEQ", sequenceName = "BUONI_SCONTO_SEQ", allocationSize = 1)
    private Integer idBuono;
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
