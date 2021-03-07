package sales.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sales.management.system.model.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
}
