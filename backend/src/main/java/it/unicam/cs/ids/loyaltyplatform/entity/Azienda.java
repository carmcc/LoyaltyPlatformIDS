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
    private String moltSistemaLivelli;  //lista numeri compresi tra 0 e 9.9
    private Float divisoreCashback;     //numero maggiore di 0
    private Float moltiplicatoreVip;    //numero maggiore di 1
}
