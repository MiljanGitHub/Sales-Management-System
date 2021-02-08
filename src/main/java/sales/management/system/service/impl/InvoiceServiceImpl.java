package sales.management.system.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import sales.management.system.model.Invoice;
import sales.management.system.repository.InvoiceRepository;
import sales.management.system.service.InvoiceService;

@Service
@Validated
public class InvoiceServiceImpl implements InvoiceService{
	
	private InvoiceRepository repository;
	
	@Inject
	public InvoiceServiceImpl(final InvoiceRepository repository) {
		super();
		this.repository = repository;
	}



	@Override
	public Invoice save(Invoice invoice) {
		return repository.save(invoice);
	}
	
}
