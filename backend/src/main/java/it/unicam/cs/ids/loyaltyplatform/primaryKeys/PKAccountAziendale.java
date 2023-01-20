package it.unicam.cs.ids.loyaltyplatform.primaryKeys;

import java.util.Objects;

public class PKAccountAziendale implements java.io.Serializable {
    private Integer seriale;
    private Integer qualeAzienda;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PKAccountAziendale that = (PKAccountAziendale) o;
        return seriale.equals(that.seriale) && qualeAzienda.equals(that.qualeAzienda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriale, qualeAzienda);
    }
}
