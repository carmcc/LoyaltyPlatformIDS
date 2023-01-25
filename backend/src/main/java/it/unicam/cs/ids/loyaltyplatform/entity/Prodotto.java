package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Prodotti")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProdotto;
    @Column(nullable = false)
    private String nome;

    public Prodotto(Integer idProdotto, String nome) {
        this.idProdotto = idProdotto;
        this.nome = nome;
    }

    public Prodotto() {}
}
