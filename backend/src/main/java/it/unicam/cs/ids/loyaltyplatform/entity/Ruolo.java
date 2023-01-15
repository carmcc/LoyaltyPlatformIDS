package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKRuolo;

@Entity
@Table(name = "Ruoli")
@IdClass(PKRuolo.class)
public class Ruolo {
    @Id
    private Integer qualeAccountAziendale;
    @Id
    private Integer qualeSeriale;
    @Id
    private String qualePermesso;
    @Column(nullable = false)
    private String nome;
    @ManyToOne(targetEntity = AccountAziendale.class)
    @JoinColumns({
            @JoinColumn(name = "qualeAccountAziendale", referencedColumnName = "qualeAzienda"),
            @JoinColumn(name = "qualeSeriale", referencedColumnName = "seriale")
                })
    private AccountAziendale accountAziendale;
    @ManyToOne(targetEntity = Permesso.class)
    @JoinColumn(name = "qualePermesso", referencedColumnName = "nomePermesso")
    private Permesso permesso;

    public Ruolo()
    {

    }
    public Ruolo(Integer qualeAccountAziendale, Integer qualeSeriale, String qualePermesso, String nome) {
        this.qualeAccountAziendale = qualeAccountAziendale;
        this.qualeSeriale = qualeSeriale;
        this.qualePermesso = qualePermesso;
        this.nome = nome;
    }

    public Integer getQualeAccountAziendale() {
        return qualeAccountAziendale;
    }

    public void setQualeAccountAziendale(Integer qualeAccountAziendale) {
        this.qualeAccountAziendale = qualeAccountAziendale;
    }

    public Integer getQualeSeriale() {
        return qualeSeriale;
    }

    public void setQualeSeriale(Integer qualeSeriale) {
        this.qualeSeriale = qualeSeriale;
    }

    public String getPermesso() {
        return qualePermesso;
    }

    public void setPermesso(String permesso) {
        qualePermesso = permesso;
    }
}
