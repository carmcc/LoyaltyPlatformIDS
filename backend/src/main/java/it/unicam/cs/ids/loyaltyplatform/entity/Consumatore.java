package it.unicam.cs.ids.loyaltyplatform.entity;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityEmail;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityPassword;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Consumatori")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Consumatore implements EntityPassword, EntityEmail
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CONSUMATORI_SEQ")
    @SequenceGenerator(name = "CONSUMATORI_SEQ", sequenceName = "CONSUMATORI_SEQ", allocationSize = 1)
    private Integer idConsumatore;
    @Column(unique = true, nullable = false)
    private String nickname;
    @Column(nullable = false)
    private Date dataDiNascita;
    @Column(nullable = false)
    private Boolean sesso;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
}
