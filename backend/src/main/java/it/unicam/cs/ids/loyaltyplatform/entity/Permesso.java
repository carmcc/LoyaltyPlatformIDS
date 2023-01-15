package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Permessi")
public class Permesso
{
    @Id //questa primary key può essere modificata
    private String nomePermesso;//TODO impostare lunghezza a 30
    @OneToMany(targetEntity = Ruolo.class)
    @JoinColumn(name = "nomePermesso", referencedColumnName = "qualePermesso")
    private List<Ruolo> ruoli;

    public Permesso(String nomePermesso) {
        this.nomePermesso = nomePermesso;
    }

    public Permesso() {

    }


    public String getNomePermesso() {
        return nomePermesso;
    }

    public void setNomePermesso(String nomePermesso) {  //TODO controllo nomi uguali
        this.nomePermesso = nomePermesso;
    }


}
