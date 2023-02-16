package it.unicam.cs.ids.loyaltyplatform.repository;

import it.unicam.cs.ids.loyaltyplatform.entity.Invito;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKInvito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitoRepository extends JpaRepository<Invito, PKInvito> {

    /**
     * Ritorna tutti gli inviti che hanno come destinatario l'azienda specificata
     * @param qualeCoalizione id dell'azienda
     * @return Lista di inviti
     */
    List<Invito> findByQualeCoalizione(Integer qualeCoalizione);

    /**
     * Cancella l'invito con l'id specificato
     * @param id id dell'invito
     */
    void deleteById(PKInvito id);

    /**
     * Ritorna tutti gli inviti che hanno come mittente l'azienda specificata
     * @param qualeAziendaInvitante id dell'azienda
     * @return Lista di inviti
     */
    List<Invito> findByQualeAziendaInvitante(Integer qualeAziendaInvitante);
}
