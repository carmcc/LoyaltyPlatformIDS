package it.unicam.cs.ids.loyaltyplatform.primaryKeys;


import java.util.Objects;

public class PKRecensione implements java.io.Serializable {
    private Integer qualeConsumatore;
    private Integer qualeAzienda;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PKRecensione that = (PKRecensione) o;
        return qualeConsumatore.equals(that.qualeConsumatore) && qualeAzienda.equals(that.qualeAzienda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualeConsumatore, qualeAzienda);
    }
}
