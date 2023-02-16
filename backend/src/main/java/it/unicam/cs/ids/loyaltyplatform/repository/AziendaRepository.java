package it.unicam.cs.ids.loyaltyplatform.repository;
import it.unicam.cs.ids.loyaltyplatform.entity.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, Integer> {

    /** Ritorna tutte le aziende che hanno aderito alla coalizione
     * @param qualeCoalizione id della coalizione
     * @return lista di aziende
     */
    List<Azienda> findByQualeCoalizione(Integer qualeCoalizione);
}
