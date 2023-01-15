package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKAccountAziendale;

import java.util.List;

@Entity
@Table(name = "AccountAziendali")
@IdClass(PKAccountAziendale.class)
public class AccountAziendale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer seriale;
    @Id
    private final Integer qualeAzienda;
    @ManyToOne
    @JoinColumn(name = "qualeAzienda", referencedColumnName = "idAzienda")
    private Azienda azienda;
    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "qualeAzienda", referencedColumnName = "qualeAccountAziendale"),
            @JoinColumn(name = "seriale", referencedColumnName = "qualeSeriale")
                })
    private List<Ruolo> ruoli;

    public AccountAziendale() {
        seriale = 0;
        qualeAzienda = 0;
    }

    public AccountAziendale(Integer seriale, Integer qualeAzienda) {
        this.seriale = seriale;
        this.qualeAzienda = qualeAzienda;
    }

    public Integer getSeriale() {
        return seriale;
    }

    public Integer getQualeAzienda() {
        return qualeAzienda;
    }
}
