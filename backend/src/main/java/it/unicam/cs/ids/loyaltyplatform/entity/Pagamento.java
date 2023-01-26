package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
@Entity
@Table(name = "Pagamenti")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue
    private Integer idPagamento;
    @Column(nullable = false)
    private Integer qualeConsumatore;
    @Column(nullable = false)
    private Date dataPagamento;
    @Column(nullable = false)
    private Float costoTotale;
}
