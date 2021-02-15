package sales.management.system.service;

import java.util.List;

import sales.management.system.dtoResponse.Partner;
import sales.management.system.model.BussinesPartner;
import sales.management.system.model.Company;

public interface BussinesPartnerService {
	
	BussinesPartner findById(int bussinesPartnerId);
	List<BussinesPartner> findAll();
	BussinesPartner save(BussinesPartner bussinesPartner);
	List<Partner> findPartners(Company company);

}
