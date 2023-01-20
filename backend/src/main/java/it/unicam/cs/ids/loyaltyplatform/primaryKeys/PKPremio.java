package it.unicam.cs.ids.loyaltyplatform.primaryKeys;

import java.util.Objects;

public class PKPremio implements java.io.Serializable {
    private Integer qualeAzienda;
    private Integer qualeProdotto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PKPremio pkPremio = (PKPremio) o;
        return qualeAzienda.equals(pkPremio.qualeAzienda) && qualeProdotto.equals(pkPremio.qualeProdotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualeAzienda, qualeProdotto);
    }
}
