package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKRecensione;
import lombok.*;
@Entity
@Table(name = "RECENSIONI")
@IdClass(PKRecensione.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Recensione {

    @Id
    private Integer qualeConsumatore;
    @Id
    private Integer qualeAzienda;
    @Column(nullable = false)
    private Integer valutazione;
    private String commento;
}
