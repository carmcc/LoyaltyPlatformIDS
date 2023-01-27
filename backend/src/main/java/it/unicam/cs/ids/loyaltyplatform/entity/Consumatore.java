package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Consumatori")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Consumatore
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CONSUMATORI_SEQ")
    @SequenceGenerator(name = "CONSUMATORI_SEQ", sequenceName = "CONSUMATORI_SEQ", allocationSize = 1)
    private Integer idConsumatore;
    @Column(unique = true, nullable = false)
    private String nickname;
    private String dataDiNascita;
    private Boolean sesso;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
}
