package sales.management.system.controller.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import sales.management.system.dtoResponse.PricelistDetailDto;
import sales.management.system.dtoResponse.PricelistDetailResponse;
import sales.management.system.dtoResponse.PricelistDto;
import sales.management.system.dtoResponse.PricelistItemResponse;
import sales.management.system.dtoResponse.PricelistResponse;
import sales.management.system.dtoResponse.RawPricelistItem;
import sales.management.system.dtoResponse.RawTax;
import sales.management.system.model.Company;
import sales.management.system.model.Pricelist;
import sales.management.system.service.CompanyService;
import sales.management.system.service.PricelistItemService;
import sales.management.system.service.PricelistService;
import sales.management.system.service.TaxService;

@Service
public class PriceListControllerImpl {
	
	@Autowired
	private PricelistItemService pricelistItemService;
	
 	@Autowired
 	private TaxService taxService;
 	
 	@Autowired
 	private MessageSource messageSource;
 	
 	@Autowired
 	private PricelistService pricelistService;
 	
 	@Autowired
 	private CompanyService companyService;
 	
	
	public PricelistItemResponse getPricelistItems(String requestedTime) {
		
		
		PricelistItemResponse response = new PricelistItemResponse();
		
		List<RawPricelistItem> rawPricelistItems = pricelistItemService.findPricelistItems(Long.valueOf(requestedTime)); // 18 jan ->

		if (!rawPricelistItems.isEmpty()){
			
			rawPricelistItems.forEach(item -> {
				
				RawTax rt = taxService.findRawTaxValuesPerCommodityGroup(item.getCommodityGroupId(), Long.valueOf(requestedTime));
				
				item.setTax(rt.getTax());
				
			});
					
			response.setCode(200);
			response.setCommodities(rawPricelistItems);
			response.setError(false);
			response.setMessage(messageSource.getMessage("pricelist.items", null, new Locale("en")));
			
		} else {
			response.setCode(200);
			response.setCommodities(null);
			response.setError(true);
			response.setMessage(messageSource.getMessage("pricelist.items.empty", null, new Locale("en")));
		}
				
		
		return response;
	}
	
	
	public PricelistResponse getPricelists() {
		
		PricelistResponse response = new PricelistResponse();
		
		Company theOnlyCompanyInTheSystem = companyService.findById(1); //hard coded Company
		
		if (theOnlyCompanyInTheSystem == null) {
			
			response.setCode(200);
			response.setError(true);
			response.setItems(null);
			response.setMessage(messageSource.getMessage("bad.company", null, new Locale("en")));
			
			return response;
		}
		
		List<PricelistDto> pricelistDtos = pricelistService.findPricelistDto(theOnlyCompanyInTheSystem);
		

		//setting date-time format of each DTO
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
		Calendar cal = Calendar.getInstance();

		pricelistDtos.forEach(dto -> {
			cal.setTimeInMillis(Long.valueOf(dto.getValidFrom()));
			dto.setValidFrom(df.format(cal.getTime()));
		});
		
		response.setCode(200);
		response.setError(false);
		response.setItems(pricelistDtos);
		response.setMessage(messageSource.getMessage("pricelist", null, new Locale("en")));
		
		return response;
		
	}
	
	public PricelistDetailResponse getPricelistsDetails(int pricelistId) {
		
		PricelistDetailResponse response = new PricelistDetailResponse();
		
		List<PricelistDetailDto> details = pricelistService.findPricelistItemDetails(pricelistId);
		
		if (!details.isEmpty()) {
			
			Pricelist pricelist = pricelistService.findById(pricelistId);
			
			Long requestedTime = Long.valueOf(pricelist.getValidFrom()); //time to look for Tax based on starting time of requested Price-list

			details.forEach(detail -> {

				//set tax rate
//				RawTax rt = taxService.findRawTaxValuesPerCommodityGroup(detail.getCommodityGroupId(), requestedTime);
				RawTax rt=new RawTax(1,10.0);// ovo sam dodao jer ovo gore puca ...
				detail.setTaxRate(rt.getTax());
				
			});	
			
		}
		
		response.setCode(200);
		response.setError(false);
		response.setDetails(details);
		response.setMessage(messageSource.getMessage("pricelist.details", null, new Locale("en")));
		
		return response;
		
	}
	

	
}
