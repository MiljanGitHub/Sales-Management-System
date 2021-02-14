package sales.management.system.service;

import java.util.List;

import sales.management.system.dtoResponse.InvoiceDto;
import sales.management.system.model.Invoice;

public interface InvoiceService {
	
	Invoice save(Invoice invoice);
	List<InvoiceDto> findInvoicesByDate(long from, long to);
	Invoice findById(int invoiceId);
	List<Invoice> findAll();
}
