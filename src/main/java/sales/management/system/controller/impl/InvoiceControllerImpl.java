package sales.management.system.controller.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import sales.management.system.dtoRequest.NewOrderRequest;
import sales.management.system.dtoResponse.InvoiceDto;
import sales.management.system.dtoResponse.StringResponse;
import sales.management.system.helper.JasperReportHelper;
import sales.management.system.model.BussinesPartner;
import sales.management.system.model.Commodity;
import sales.management.system.model.Company;
import sales.management.system.model.Invoice;
import sales.management.system.model.InvoiceItem;
import sales.management.system.model.enums.EInvoiceStatus;
import sales.management.system.service.BussinesPartnerService;
import sales.management.system.service.CommodityService;
import sales.management.system.service.CompanyService;
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
	private CommodityService commodityService;
	
	@Autowired
	private JasperReportHelper jasperHelper;
	
	public StringResponse addNewInvoice(NewOrderRequest request) {
		
		String message = null;
		boolean error = false;
		
		Invoice newInvoice = new Invoice(); newInvoice.setItems(new ArrayList<InvoiceItem>());
		
		newInvoice.setInvoiceNumber(String.valueOf(System.currentTimeMillis()));
		newInvoice.setInvoiceDate(String.valueOf(System.currentTimeMillis()));
		newInvoice.setCurrencyDate(String.valueOf(System.currentTimeMillis()));
		newInvoice.setStatus(EInvoiceStatus.NOT_PAID);
		newInvoice.setBasisTotal(request.getTotalBasis());
		newInvoice.setTaxTotal(request.getTaxAmount());
		newInvoice.setTotalAmmount(request.getTotalBasis());
		
		BussinesPartner bp = bussinesPartnerService.findById(request.getBussinesPartnerId());
		
		if (bp == null) message = "partner.error"; error = true;
		
		Company company = companyService.findById(1); //hard coded Company
		
		if (company == null) message = "company.error"; error = true;
		
		bp.addInvoice(newInvoice); company.addInvoice(newInvoice);
		
		
		request.getItems().forEach(itemDto -> {
			
			InvoiceItem invoiceItem = new InvoiceItem(itemDto);
			
			newInvoice.addInvoiceItem(invoiceItem);
			
			Commodity commodity = commodityService.findById(itemDto.getCommodityId());
			
			commodity.addInvoiceItem(invoiceItem);
			
		});
		
		Invoice newlySavedInvoice = invoiceService.save(newInvoice);
		
		if (newlySavedInvoice != null && newlySavedInvoice.getItems().size() == request.getItems().size()) {
			message="successfully.invoice"; error = false;
		} else message="error.invoice"; error = true;
		
		jasperHelper.generateJasperReport(newlySavedInvoice);

		return new StringResponse(message, error, 200);
	}
	
	public List<InvoiceDto> findInvoices(String fromDate, String toDate){
		
		long from = Long.valueOf(fromDate);
		long to = Long.valueOf(toDate);
		
		List<InvoiceDto> invoicesDto = invoiceService.findInvoicesByDate(from, to);
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
		Calendar cal = Calendar.getInstance();

		invoicesDto.forEach(dto -> {
			cal.setTimeInMillis(Long.valueOf(dto.getInvoiceDate()));
			dto.setInvoiceDate(df.format(cal.getTime()));
		});

//		String bookURL=jasperHelper.generateInvoiceBook(invoicesDto,fromDate,toDate);
//		System.out.println(bookURL);
		return invoicesDto;
		
	}
	
	@Async
	public void updateInvoiceStatus() {
		
		List<Invoice> invoices = invoiceService.findAll();
		
		invoices.forEach(invoice -> {
			System.out.print("Go to external service and update status of Invoice");
		});
		
	}
	
}
