package it.unicam.cs.ids.loyaltyplatform.primaryKeys;

import java.util.Objects;

public class PKSpesa implements java.io.Serializable {
    private Integer qualePagamento;
    private Integer qualeProdotto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PKSpesa pkSpesa = (PKSpesa) o;
        return qualePagamento.equals(pkSpesa.qualePagamento) && qualeProdotto.equals(pkSpesa.qualeProdotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualePagamento, qualeProdotto);
    }
}
