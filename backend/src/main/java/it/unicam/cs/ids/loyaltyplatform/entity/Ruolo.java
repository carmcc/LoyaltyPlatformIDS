package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKRuolo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Ruoli")
@IdClass(PKRuolo.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Ruolo {
    @Id
    private Integer qualeAccountAziendale;
    @Id
    private Integer qualeSeriale;
    @Id
    private String qualePermesso;
    @Column(nullable = false)
    private String nome;

    public Ruolo(Integer qualeAccountAziendale, Integer qualeSeriale, String qualePermesso, String nome) {
        this.qualeAccountAziendale = qualeAccountAziendale;
        this.qualeSeriale = qualeSeriale;
        this.qualePermesso = qualePermesso;
        this.nome = nome;
    }

    public Ruolo() {}
}
