package sales.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sales.management.system.model.Pricelist;

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Integer> {

}
