package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKRuolo;
import lombok.*;
@Entity
@Table(name = "RUOLI")
@IdClass(PKRuolo.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Ruolo {
    @Id
    private Integer qualeAzienda;
    @Id
    private Integer qualeSeriale;
    @Id
    private String qualePermesso;
    @Column(nullable = false)
    private String nome;
}
