package sales.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sales.management.system.model.BussinesPartner;

@Repository
public interface BussinesPartnerRepository extends JpaRepository<BussinesPartner, Integer>{

}
