package sales.management.system.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import sales.management.system.model.BussinesPartner;
import sales.management.system.repository.BussinesPartnerRepository;
import sales.management.system.service.BussinesPartnerService;

@Service
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
	
}
