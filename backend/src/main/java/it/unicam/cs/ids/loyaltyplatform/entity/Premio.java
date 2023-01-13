package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import primaryKeys.PKPremio;

@Entity
@Table(name = "Premi")
@IdClass(PKPremio.class)
public class Premio {   //TODO aggiungere foreight keys
                        // foreign key (quale_azienda) references aziende (id_azienda), foreign key (quale_prodotto) references prodotti (id_prodotto));
    @Id
    private final Integer qualeAzienda;
    @Id
    private final Integer qualeProdotto;
    @Column(nullable = false)
    private Integer costo;

    public Premio() {
        qualeAzienda = 0;
        qualeProdotto = 0;
    }

    public Premio(Integer qualeAzienda, Integer qualeProdotto, Integer costo) {
        this.qualeAzienda = qualeAzienda;
        this.qualeProdotto = qualeProdotto;
        this.costo = costo;
    }

    public Integer getQualeAzienda() {
        return qualeAzienda;
    }

    public Integer getQualeProdotto() {
        return qualeProdotto;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }
}
