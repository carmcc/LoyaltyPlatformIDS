package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Prodotti")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODOTTI_SEQ")
    @SequenceGenerator(name = "PRODOTTI_SEQ", sequenceName = "PRODOTTI_SEQ", allocationSize = 1)
    private Integer idProdotto;
    @Column(nullable = false, unique = true)
    private String nome;
}
