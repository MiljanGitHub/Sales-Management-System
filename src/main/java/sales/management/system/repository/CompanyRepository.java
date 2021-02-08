package sales.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sales.management.system.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
