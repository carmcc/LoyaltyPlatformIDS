package it.unicam.cs.ids.loyaltyplatform.repository;
import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdesioneRepository extends JpaRepository<Adesione, Integer> {
}

