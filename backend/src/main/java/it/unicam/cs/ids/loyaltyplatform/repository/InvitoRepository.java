package it.unicam.cs.ids.loyaltyplatform.repository;

import it.unicam.cs.ids.loyaltyplatform.entity.Invito;
import it.unicam.cs.ids.loyaltyplatform.primaryKeys.PKInvito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitoRepository extends JpaRepository<Invito, PKInvito> {
    List<Invito> findByQualeCoalizione(Integer qualeCoalizione);
    void deleteById(PKInvito id);
    List<Invito> findByQualeAziendaInvitante(Integer qualeAziendaInvitante);
}
