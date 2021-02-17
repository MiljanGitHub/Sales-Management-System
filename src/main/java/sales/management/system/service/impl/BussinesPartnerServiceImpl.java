package sales.management.system.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import sales.management.system.dtoResponse.Partner;
import sales.management.system.model.BussinesPartner;
import sales.management.system.model.Company;
import sales.management.system.repository.BussinesPartnerRepository;
import sales.management.system.service.BussinesPartnerService;

@Service
@Validated
public class BussinesPartnerServiceImpl implements BussinesPartnerService{
	
	private BussinesPartnerRepository repository;
	
	@Inject
	public BussinesPartnerServiceImpl(final BussinesPartnerRepository bussinesPartnerRepository) {
		this.repository = bussinesPartnerRepository;
	}

	@Override
	public BussinesPartner findById(int bussinesPartnerId) {
		return repository.getOne(bussinesPartnerId);

	}

	@Override
	public List<BussinesPartner> findAll() {
		return repository.findAll();
	}

	@Override
	public BussinesPartner save(BussinesPartner bussinesPartner) {
		return repository.save(bussinesPartner);
	}

	@Override
	public List<Partner> findPartners(Company company) {
		return repository.findPartners(company);
	}
	
}
