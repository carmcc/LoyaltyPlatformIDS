package it.unicam.cs.ids.loyaltyplatform.repository;
import it.unicam.cs.ids.loyaltyplatform.entity.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecensioneRepository extends JpaRepository<Recensione, Integer> {
}

