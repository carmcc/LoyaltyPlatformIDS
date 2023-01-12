package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ruoli")
public class Ruolo
{
    @Id
    private Integer qualeAccountAziendale;//TODO chiavi secondarie
    private Integer qualeSeriale;
    private String qualePermesso;
    private String nome;

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
