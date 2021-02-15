package sales.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sales.management.system.dtoResponse.Partner;
import sales.management.system.model.BussinesPartner;
import sales.management.system.model.Company;

@Repository
public interface BussinesPartnerRepository extends JpaRepository<BussinesPartner, Integer>{
		
	@Query(value = "SELECT new sales.management.system.dtoResponse.Partner(partner.id, partner.name, partner.address, partner.fax, partner.email, partner.fax) FROM BussinesPartner partner WHERE partner.company = :company ")
	List<Partner> findPartners(@Param("company") Company company);
	
}
