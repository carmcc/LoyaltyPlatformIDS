package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Aziende")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Azienda
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAzienda;
    private Integer qualeCoalizione;
    @Column(unique = true,nullable = false)
    private String nomeAzienda;
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


    public Azienda(Integer id, String nomeAzienda, String email, String password, String IVA, String sede, String referral, String moltSistemaLivelli, float divisoreCashback, float moltiplicatoreVip) {
        this.idAzienda = id;
        this.qualeCoalizione = -1;
        this.nomeAzienda = nomeAzienda;
        this.email = email;
        this.password = password;
        this.IVA = IVA;
        this.referral = referral;
        this.moltSistemaLivelli = moltSistemaLivelli;
        this.divisoreCashback = divisoreCashback;
        this.moltiplicatoreVip = moltiplicatoreVip;
    }

    public Azienda() {}
}
