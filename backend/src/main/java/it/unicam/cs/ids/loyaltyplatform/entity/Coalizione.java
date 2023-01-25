package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Coalizioni")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class Coalizione
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCoalizione;
    @Column(nullable = false)
    private Float parametroBuoniSpesa;//buoni spesa ogni tot euro
    @Column(nullable = false)
    private Boolean condivisionePunti;
    private Float penalitaCondivisione;
    private Integer percentualeRitiroPremi; //Si possono usare i punti ottenuti da altre aziende, ma per
                                            // ritirare il premio da una di queste devi avere guadagnato da essa almeno la percentuale indicata.

    public Coalizione(Integer id, boolean condivisionePunti, float penalitaCondivisione, Integer percentualeRitiroPremi) {
        this.idCoalizione = id;
        this.condivisionePunti = condivisionePunti;
        this.penalitaCondivisione = penalitaCondivisione;
        this.percentualeRitiroPremi = percentualeRitiroPremi;
    }
    public Coalizione() {}
}
