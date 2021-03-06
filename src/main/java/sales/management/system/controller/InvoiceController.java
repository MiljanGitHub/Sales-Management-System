package sales.management.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sales.management.system.controller.impl.InvoiceControllerImpl;
import sales.management.system.dtoRequest.NewOrderRequest;
import sales.management.system.dtoResponse.InvoiceDto;
import sales.management.system.dtoResponse.StringResponse;
import sales.management.system.helper.JasperReportHelper;


@RestController
@RequestMapping("invoice/")
@EnableAsync
public class InvoiceController {
	
	@Autowired
	private InvoiceControllerImpl invoiceControllerImpl;

	@Autowired
	private JasperReportHelper jasperHelper;
	
	@GetMapping(value = "invoiceBook/{fromDate}/{toDate}")
	public List<InvoiceDto> findInvoices(@PathVariable String fromDate, @PathVariable String toDate){
		
		List<InvoiceDto> response = invoiceControllerImpl.findInvoices(fromDate, toDate);
		
		return response;
		
	}

	@GetMapping(value = "generateInvoiceBookReport/{fromDate}/{toDate}")
	public StringResponse generateReport(@PathVariable String fromDate, @PathVariable String toDate){
		StringResponse stringResponse=new StringResponse();

		try {
			List<InvoiceDto> response = invoiceControllerImpl.findInvoices(fromDate, toDate);
			String url = jasperHelper.generateInvoiceBook(invoiceControllerImpl.findInvoices(fromDate, toDate), fromDate, toDate);
			System.out.println("aaa" +url);
			stringResponse.setMessage(url);
			return stringResponse;
		}catch (Exception e){
			e.printStackTrace();
			return stringResponse;
		}

	}
	
	@PostMapping(value = "create")
	public StringResponse addNewInvoice(@RequestBody NewOrderRequest request) {
		
		StringResponse response = invoiceControllerImpl.addNewInvoice(request);
		
		return response;
		
	}
	
	@Scheduled(cron = "0 0 18 * * ?", zone = "Europe/Belgrade") //every day at 18h
	public void updateInvoiceStatus() {
		
		invoiceControllerImpl.updateInvoiceStatus();
		
	}

}
