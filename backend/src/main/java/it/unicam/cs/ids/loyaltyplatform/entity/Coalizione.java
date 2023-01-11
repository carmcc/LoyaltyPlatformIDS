package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Coalizioni")
public class Coalizione
{
    @Id
    private Integer id;

    //buoni spesa ogni tot euro
    private boolean condivisionePunti;

    private float penalitaCondivisione;

    //percentuale minima
    private Integer percentualeRitiroPremi;

    public Coalizione() {
    }
    public Coalizione(Integer id, boolean condivisionePunti, float penalitaCondivisione, Integer percentualeRitiroPremi) {
        this.id = id;
        this.condivisionePunti = condivisionePunti;
        this.penalitaCondivisione = penalitaCondivisione;
        this.percentualeRitiroPremi = percentualeRitiroPremi;
    }



    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
