package it.unicam.cs.ids.loyaltyplatform.primaryKeys;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class PKRuolo implements java.io.Serializable {
    private Integer qualeAzienda;
    private Integer qualeSeriale;
    private String qualePermesso;
}
