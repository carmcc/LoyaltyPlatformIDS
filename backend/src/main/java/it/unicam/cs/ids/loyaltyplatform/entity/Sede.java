package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "SEDI")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEDI_SEQ")
    @SequenceGenerator(name = "SEDI_SEQ", sequenceName = "SEDI_SEQ", allocationSize = 1)
    private Integer idSede;
    @Column(nullable = false)
    private Integer qualeAzienda;
    @Column(nullable = false)
    private String via;
    @Column(nullable = false)
    private String cap;
    @Column(nullable = false)
    private String regione;
    @Column(nullable = false)
    private String citta;
    @Column(nullable = false)
    private String civico;
}
