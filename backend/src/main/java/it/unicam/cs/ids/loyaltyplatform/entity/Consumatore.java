package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Consumatori")
public class Consumatore
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer idConsumatore;
    @Column(unique = true, nullable = false)
    private String nickname;
    private String dataDiNascita;
    private boolean sesso;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public Consumatore() {
        idConsumatore = 0;
    }

    public Consumatore(Integer id, String nickname, String dataDiNascita, boolean sesso, String email, String password) {
        this.idConsumatore = id;
        this.nickname = nickname;
        this.dataDiNascita = dataDiNascita;
        this.sesso = sesso;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return idConsumatore;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public boolean isSesso() {
        return sesso;
    }

    public void setSesso(boolean sesso) {
        this.sesso = sesso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
