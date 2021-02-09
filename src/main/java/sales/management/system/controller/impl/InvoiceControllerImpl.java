package sales.management.system.controller.impl;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sales.management.system.dtoRequest.NewOrderRequest;
import sales.management.system.dtoResponse.StringResponse;
import sales.management.system.model.BussinesPartner;
import sales.management.system.model.Company;
import sales.management.system.model.Invoice;
import sales.management.system.model.InvoiceItem;
import sales.management.system.model.enums.EInvoiceStatus;
import sales.management.system.service.BussinesPartnerService;
import sales.management.system.service.CompanyService;
import sales.management.system.service.InvoiceItemService;
import sales.management.system.service.InvoiceService;

@Service
public class InvoiceControllerImpl {
	
	@Autowired
	private BussinesPartnerService bussinesPartnerService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceItemService invoiceItemService;
	
	public StringResponse addNewInvoice(NewOrderRequest request) {
		
		
		
		System.out.println("bussinesPartnerId " + request.getBussinesPartnerId());
		System.out.println("totalBasis " + request.getTotalBasis());
		System.out.println("totalTax " + request.getTotalTax());
		System.out.println("total " + request.getTotal());
		
		System.out.println();
		request.getOrderItem().forEach(item -> {
			

			System.out.println(item.getCommodityId());
			System.out.println(item.getAmount());
			System.out.println(item.getUnitPrice());
			System.out.println(item.getTaxPercentage());
			System.out.println(item.getTaxAmount());
			System.out.println(item.getTotal());
		});
		
		
		String message = null;
		
		Invoice newInvoice = new Invoice(); //newInvoice.setItems(new HashSet<InvoiceItem>());
		
		BussinesPartner bp = bussinesPartnerService.findById(request.getBussinesPartnerId());
		
		if (bp == null) message = "partner.error";
		
		Company company = companyService.findById(1); //hard coded Company
		
		if (company == null) message = "company.error";
		
		bp.addInvoice(newInvoice); company.addInvoice(newInvoice);

		newInvoice.setInvoiceNumber(String.valueOf(System.currentTimeMillis()));
		newInvoice.setInvoiceDate(String.valueOf(System.currentTimeMillis()));
		newInvoice.setCurrencyDate(String.valueOf(System.currentTimeMillis()));
		newInvoice.setStatus(EInvoiceStatus.NOT_PAID);
		newInvoice.setBasisTotal(request.getTotalBasis());
		newInvoice.setTaxTotal(request.getTotalTax());
		newInvoice.setTotalAmmount(request.getTotalBasis());
		
//		request.getOrderItem().forEach(item -> {
//			
//			InvoiceItem i = new InvoiceItem(item);
//			//i.addOrder(newInvoice);
//			//newInvoice.getItems().add(i);
//			i.setInvoice(newInvoice);
//			
//
//		});
		
		
		Invoice savedInvoice = invoiceService.save(newInvoice);
		
		
		
		List<InvoiceItem> itms = request.getOrderItem().stream().map(i -> new InvoiceItem(i, savedInvoice)).collect(Collectors.toList()); 
		
		invoiceItemService.saveAll(itms);
		
		return new StringResponse("s", false, 200);
	}
	
}
