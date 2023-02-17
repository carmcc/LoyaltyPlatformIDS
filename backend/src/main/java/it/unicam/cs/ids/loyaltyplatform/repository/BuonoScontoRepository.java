package it.unicam.cs.ids.loyaltyplatform.repository;
import it.unicam.cs.ids.loyaltyplatform.entity.BuonoSconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuonoScontoRepository extends JpaRepository<BuonoSconto, Integer> {
    List<BuonoSconto> getBuonoScontoByQualeConsumatore(Integer idConsumatore);
}
