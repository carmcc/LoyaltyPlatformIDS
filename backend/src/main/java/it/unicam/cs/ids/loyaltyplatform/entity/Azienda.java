package it.unicam.cs.ids.loyaltyplatform.entity;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityEmail;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityPassword;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "AZIENDE")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Azienda implements EntityPassword, EntityEmail
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "AZIENDE_SEQ")
    @SequenceGenerator(name = "AZIENDE_SEQ", sequenceName = "AZIENDE_SEQ", allocationSize = 1)
    private Integer idAzienda;
    private Integer qualeCoalizione;
    @Column(unique = true,nullable = false)
    private String nomeAzienda;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String IVA;
    @Column(nullable = false)
    private String referral;
    private String moltSistemaLivelli;
    private Float divisoreCashback;
    private Float moltiplicatoreVip;
}
