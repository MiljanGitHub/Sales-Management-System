package sales.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.management.system.model.Company;

public interface CopmanyRepository extends JpaRepository<Company, Integer> {
}
