package sales.management.system.service;

import java.util.List;

import sales.management.system.model.InvoiceItem;

public interface InvoiceItemService {
	
	InvoiceItem save(InvoiceItem invoiceItem);
	List<InvoiceItem> saveAll(List<InvoiceItem> invoiceItems);
}
