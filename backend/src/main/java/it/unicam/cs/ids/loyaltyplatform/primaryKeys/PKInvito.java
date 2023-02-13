package it.unicam.cs.ids.loyaltyplatform.primaryKeys;

import lombok.*;

@EqualsAndHashCode
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
public class PKInvito implements java.io.Serializable{
        private Integer qualeAziendaInvitante;
        private Integer qualeAziendaInvitata;
}
