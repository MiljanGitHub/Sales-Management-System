package sales.management.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sales.management.system.controller.impl.InvoiceControllerImpl;
import sales.management.system.dtoResponse.InvoiceDto;


@RestController
@RequestMapping("invoice/")
@EnableAsync
public class InvoiceController {
	
	@Autowired
	private InvoiceControllerImpl invoiceControllerImpl;
	
	@GetMapping(value = "invoiceBook/{fromDate}/{toDate}")
	public List<InvoiceDto> findInvoices(@PathVariable String fromDate, @PathVariable String toDate){
		
		List<InvoiceDto> response = invoiceControllerImpl.findInvoices(fromDate, toDate);
		
		return response;
		
	}
	
	@Scheduled(cron = "0 0 18 * * ?", zone = "Europe/Belgrade") //every day at 18h
	public void updateInvoiceStatus() {
		
		invoiceControllerImpl.updateInvoiceStatus();
		
	}

}
