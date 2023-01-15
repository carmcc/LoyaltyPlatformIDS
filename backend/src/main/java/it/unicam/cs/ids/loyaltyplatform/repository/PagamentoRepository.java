package it.unicam.cs.ids.loyaltyplatform.repository;
import it.unicam.cs.ids.loyaltyplatform.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
