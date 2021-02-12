package sales.management.system.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import sales.management.system.controller.impl.InvoiceControllerImpl;
import sales.management.system.dtoRequest.NewOrderRequest;
import sales.management.system.dtoResponse.StringResponse;
import sales.management.system.service.JasperReportService;


@RestController
@RequestMapping("invoice/")
public class InvoiceController {
	
	@Autowired
	private InvoiceControllerImpl invoiceControllerImpl;

	@Autowired
	private JasperReportService jasperReportService;
	
	@PostMapping(value = "create")
	public StringResponse addNewInvoice(@RequestBody NewOrderRequest newOrder) {
		
		StringResponse response = invoiceControllerImpl.addNewInvoice(newOrder);
		
		return response;
	}


	@GetMapping(value = "getReport")
	public String getAccounts() throws FileNotFoundException, JRException {
		return jasperReportService.exportReport("pdf");
	}

}
