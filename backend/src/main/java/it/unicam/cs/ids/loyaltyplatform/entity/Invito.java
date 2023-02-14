package it.unicam.cs.ids.loyaltyplatform.entity;

import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKInvito;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INVITI")
@IdClass(PKInvito.class)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Invito {
    @Id
    private Integer qualeAziendaInvitante;
    @Id
    private Integer qualeAziendaInvitata;
    @Column(nullable = false)
    private Integer qualeCoalizione;
    private Boolean stato;
}
