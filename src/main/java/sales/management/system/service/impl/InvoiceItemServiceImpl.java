package sales.management.system.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import sales.management.system.model.InvoiceItem;
import sales.management.system.repository.InvoiceItemRepository;
import sales.management.system.service.InvoiceItemService;

@Service
@Validated
public class InvoiceItemServiceImpl implements InvoiceItemService{
	
	private InvoiceItemRepository repository;
	
	@Inject
	public InvoiceItemServiceImpl(final InvoiceItemRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public InvoiceItem save(InvoiceItem invoiceItem) {
		
		return repository.save(invoiceItem);
	}

	@Override
	public List<InvoiceItem> saveAll(List<InvoiceItem> invoiceItems) {
		
		return repository.saveAll(invoiceItems);
	}



}
