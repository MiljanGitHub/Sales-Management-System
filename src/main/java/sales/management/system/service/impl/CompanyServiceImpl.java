package sales.management.system.service.impl;

import org.springframework.stereotype.Service;

import sales.management.system.model.Company;
import sales.management.system.repository.CompanyRepository;
import sales.management.system.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	private CompanyRepository repository;
	
	public CompanyServiceImpl(final CompanyRepository repository) {
		this.repository = repository;
	}

	@Override
	public Company findById(int companyId) {
		return repository.getOne(companyId);
	}

}
