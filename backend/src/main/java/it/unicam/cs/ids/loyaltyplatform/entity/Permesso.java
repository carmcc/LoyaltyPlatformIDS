package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Permessi")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Permesso
{
    @Id //questa primary key può essere modificata
    private String nomePermesso;//TODO impostare lunghezza a 30
}
