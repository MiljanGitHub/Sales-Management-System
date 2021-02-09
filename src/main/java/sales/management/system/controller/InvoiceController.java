package sales.management.system.controller;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sales.management.system.controller.impl.InvoiceControllerImpl;
import sales.management.system.dtoRequest.NewOrderRequest;
import sales.management.system.dtoResponse.CommodityDto;
import sales.management.system.dtoResponse.StringResponse;
import sales.management.system.model.Commodity;
import sales.management.system.service.JasperReportService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


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
