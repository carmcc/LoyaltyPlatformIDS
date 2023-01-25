package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Coalizioni")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Coalizione
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer idCoalizione;
    @Column(nullable = false)
    private Float parametroBuoniSpesa;//buoni spesa ogni tot euro
    @Column(nullable = false)
    @NonNull
    private Boolean condivisionePunti;
    @NonNull
    private Float penalitaCondivisione;
    @NonNull
    private Integer percentualeRitiroPremi; //Si possono usare i punti ottenuti da altre aziende, ma per
                                            // ritirare il premio da una di queste devi avere guadagnato da essa almeno la percentuale indicata.

}
