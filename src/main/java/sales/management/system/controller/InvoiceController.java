package sales.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sales.management.system.controller.impl.InvoiceControllerImpl;
import sales.management.system.dtoRequest.NewOrderRequest;
import sales.management.system.dtoResponse.StringResponse;


@RestController
@RequestMapping("invoice/")
public class InvoiceController {
	
	@Autowired
	private InvoiceControllerImpl invoiceControllerImpl;
	
	@PostMapping(value = "create")
	public StringResponse addNewInvoice(@RequestBody NewOrderRequest newOrder) {
		
		StringResponse response = invoiceControllerImpl.addNewInvoice(newOrder);
		
		return response;
	}



}
