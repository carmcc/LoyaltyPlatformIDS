package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Prodotti")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer idProdotto;
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nome;

    public Prodotto() {
        idProdotto = 0;
    }

    public Prodotto(Integer idProdotto, String nome) {
        this.idProdotto = idProdotto;
        this.nome = nome;
    }

    public Integer getIdProdotto() {
        return idProdotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
