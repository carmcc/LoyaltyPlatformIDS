package it.unicam.cs.ids.loyaltyplatform.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.unicam.cs.ids.loyaltyplatform.entity.Coalizione;
@Repository
public interface CoalizioneRepository extends JpaRepository<Coalizione, Integer> {
}

