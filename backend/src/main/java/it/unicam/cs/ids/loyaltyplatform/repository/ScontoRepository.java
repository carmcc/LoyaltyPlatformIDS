package it.unicam.cs.ids.loyaltyplatform.repository;
import it.unicam.cs.ids.loyaltyplatform.entity.Sconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScontoRepository extends JpaRepository<Sconto, Integer> {
}
