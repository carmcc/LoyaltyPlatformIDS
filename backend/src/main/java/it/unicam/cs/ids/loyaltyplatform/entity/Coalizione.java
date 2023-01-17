package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Coalizioni")
public class Coalizione
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer idCoalizione;
    @Column(nullable = false)
    private Float parametroBuoniSpesa;//buoni spesa ogni tot euro
    @Column(nullable = false)
    private Boolean condivisionePunti;
    private Float penalitaCondivisione;
    private Integer percentualeRitiroPremi; //Si possono usare i punti ottenuti da altre aziende,
                                            // ma per ritirare il premio da una di queste devi avere guadagnato da essa almeno la percentuale indicata.
    @OneToMany(targetEntity = Azienda.class)
    @JoinColumn(name = "idCoalizione", referencedColumnName = "qualeCoalizione")
    private List<Azienda> aziende;

    public Coalizione() {
        idCoalizione = 0;
    }
    public Coalizione(Integer id, boolean condivisionePunti, float penalitaCondivisione, Integer percentualeRitiroPremi) {
        this.idCoalizione = id;
        this.condivisionePunti = condivisionePunti;
        this.penalitaCondivisione = penalitaCondivisione;
        this.percentualeRitiroPremi = percentualeRitiroPremi;
    }



    //getters and setters
    public Integer getId() {
        return idCoalizione;
    }

    public float getParametroBuoniSpesa() {
        return parametroBuoniSpesa;
    }

    public void setParametroBuoniSpesa(float parametroBuoniSpesa) {
        this.parametroBuoniSpesa = parametroBuoniSpesa;
    }

    public boolean isCondivisionePunti() {
        return condivisionePunti;
    }

    public void setCondivisionePunti(boolean condivisionePunti) {
        this.condivisionePunti = condivisionePunti;
    }

    public float getPenalitaCondivisione() {
        return penalitaCondivisione;
    }

    public void setPenalitaCondivisione(float penalitaCondivisione) {
        this.penalitaCondivisione = penalitaCondivisione;
    }

    public Integer getPercentualeRitiroPremi() {
        return percentualeRitiroPremi;
    }

    public void setPercentualeRitiroPremi(Integer percentualeRitiroPremi) {
        this.percentualeRitiroPremi = percentualeRitiroPremi;
    }
}