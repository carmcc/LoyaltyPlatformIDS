package it.unicam.cs.ids.loyaltyplatform.repository;
import it.unicam.cs.ids.loyaltyplatform.entity.Consumatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumatoreRepository extends JpaRepository<Consumatore, Integer> {
}

