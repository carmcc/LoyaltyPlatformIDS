package it.unicam.cs.ids.loyaltyplatform.primaryKeys;

import java.util.Objects;

public class PKRuolo implements java.io.Serializable {
    private Integer qualeAccountAziendale;
    private Integer qualeSeriale;
    private String qualePermesso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PKRuolo pkRuolo = (PKRuolo) o;
        return qualeAccountAziendale.equals(pkRuolo.qualeAccountAziendale) && qualeSeriale.equals(pkRuolo.qualeSeriale) && qualePermesso.equals(pkRuolo.qualePermesso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualeAccountAziendale, qualeSeriale, qualePermesso);
    }
}
