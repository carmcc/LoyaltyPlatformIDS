package it.unicam.cs.ids.loyaltyplatform.primaryKeys;

import java.util.Objects;

public class PKSconto implements java.io.Serializable {
    private Integer qualeAzienda;
    private Integer qualeProdotto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PKSconto pkSconto = (PKSconto) o;
        return qualeAzienda.equals(pkSconto.qualeAzienda) && qualeProdotto.equals(pkSconto.qualeProdotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualeAzienda, qualeProdotto);
    }
}
