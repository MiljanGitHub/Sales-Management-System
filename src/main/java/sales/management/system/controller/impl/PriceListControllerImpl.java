package sales.management.system.controller.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import sales.management.system.controller.PricelistItemResponse;
import sales.management.system.dtoResponse.RawPricelistItem;
import sales.management.system.dtoResponse.RawTax;
import sales.management.system.service.PricelistItemService;
import sales.management.system.service.TaxService;

@Service
public class PriceListControllerImpl {
	
	@Autowired
	private PricelistItemService pricelistItemService;
	
 	@Autowired
 	private TaxService taxService;
 	
 	@Autowired
 	private MessageSource messageSource;
 	
	
	public PricelistItemResponse getPricelistItems(String requestedTime) {
		
		
		PricelistItemResponse response = new PricelistItemResponse();
		
		List<RawPricelistItem> rawPricelistItems = pricelistItemService.findPricelistItems(Long.valueOf(requestedTime)); // 18 jan ->

		if (!rawPricelistItems.isEmpty()){
			
			rawPricelistItems.forEach(item -> {
				
				RawTax rt = taxService.findRawTaxValuesPerCommodityGroup(item.getCommodityGroupId(), Long.valueOf(requestedTime));
				
				item.setTax(rt.getTax());
				
			});
					
			response.setCode(200);
			response.setPricelistItems(rawPricelistItems);
			response.setError(false);

			response.setMessage(messageSource.getMessage("pricelist.items", null, new Locale("en")));

	
			
		} else {
			response.setCode(200);
			response.setPricelistItems(null);
			response.setError(true);
			response.setMessage(messageSource.getMessage("pricelist.items.empty", null, new Locale("en")));
		}
				
		
		return response;
	}
}
