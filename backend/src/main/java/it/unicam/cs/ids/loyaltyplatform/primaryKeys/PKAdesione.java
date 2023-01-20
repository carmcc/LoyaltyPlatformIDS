package it.unicam.cs.ids.loyaltyplatform.primaryKeys;

import java.util.Objects;

public class PKAdesione implements java.io.Serializable {
    private Integer qualeAzienda;
    private Integer qualeConsumatore;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PKAdesione that = (PKAdesione) o;
        return qualeAzienda.equals(that.qualeAzienda) && qualeConsumatore.equals(that.qualeConsumatore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualeAzienda, qualeConsumatore);
    }
}
