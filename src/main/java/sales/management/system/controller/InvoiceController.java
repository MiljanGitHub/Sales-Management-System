package sales.management.system.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sales.management.system.controller.impl.InvoiceControllerImpl;
import sales.management.system.dto.PricelistItemResponse;

@RestController
@RequestMapping("invoice/")
public class InvoiceController {
	
	@Autowired
	InvoiceControllerImpl invoiceControllerImpl;
	
	@RequestMapping(value = "pricelistItems/{requestedTime}", method = RequestMethod.GET) 
	public PricelistItemResponse getPricelistItems(@PathParam("requestedTime") String requestedTime) {
		
		PricelistItemResponse response = invoiceControllerImpl.getPricelistItems(requestedTime);
		
		return response;
	}
	
	
	
}