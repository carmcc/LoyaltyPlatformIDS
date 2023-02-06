package it.unicam.cs.ids.loyaltyplatform.primaryKeys;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class PKAccountAziendale implements java.io.Serializable {
    private Integer seriale;
    private Integer qualeAzienda;

}
