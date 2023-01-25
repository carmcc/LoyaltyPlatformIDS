package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Permessi")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Permesso
{
    @Id //questa primary key può essere modificata
    private String nomePermesso;//TODO impostare lunghezza a 30


    public Permesso(String nomePermesso) {
        this.nomePermesso = nomePermesso;
    }

    public Permesso() {}
}
