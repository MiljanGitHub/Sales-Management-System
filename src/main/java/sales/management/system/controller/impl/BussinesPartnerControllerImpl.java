package sales.management.system.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sales.management.system.dtoResponse.Partner;
import sales.management.system.model.BussinesPartner;
import sales.management.system.model.Company;
import sales.management.system.service.BussinesPartnerService;
import sales.management.system.service.CompanyService;

@Service
public class BussinesPartnerControllerImpl {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BussinesPartnerService bussinesPartnerService;
	
	public List<Partner> findAll(){
		
		Company company = companyService.findById(1); //hard codded Company
		
		List<Partner> response = bussinesPartnerService.findPartners(company);
		
		return response;
		
	}
	
	public Partner addPartner(Partner newPartner){
		
		Company company = companyService.findById(1); //hard codded Company
		
		BussinesPartner bp = new BussinesPartner(newPartner);
		
		company.addBussinesPartner(bp);
		
		BussinesPartner partner = bussinesPartnerService.save(bp);
		
		newPartner.setId(partner.getId());
		
		return newPartner;

	}
	
	
}
