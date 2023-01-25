package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Consumatori")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Consumatore
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsumatore;
    @Column(unique = true, nullable = false)
    private String nickname;
    private String dataDiNascita;
    private Boolean sesso;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public Consumatore(Integer id, String nickname, String dataDiNascita, boolean sesso, String email, String password) {
        this.idConsumatore = id;
        this.nickname = nickname;
        this.dataDiNascita = dataDiNascita;
        this.sesso = sesso;
        this.email = email;
        this.password = password;
    }
    public Consumatore() {}
}
