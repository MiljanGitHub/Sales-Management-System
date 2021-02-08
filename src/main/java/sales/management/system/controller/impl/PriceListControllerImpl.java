package sales.management.system.controller.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import sales.management.system.dtoResponse.CommodityKey;
import sales.management.system.dtoResponse.ItemDto;
import sales.management.system.dtoResponse.PricelistItemDto;
import sales.management.system.dtoResponse.PricelistItemResponse;
import sales.management.system.dtoResponse.RawPricelistItem;
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
				
			Map<CommodityKey, List<RawPricelistItem>> groupedRawPricelistItems = rawPricelistItems.stream()
																					.collect(Collectors.groupingBy(RawPricelistItem::getCommodityKey));
			
			List<ItemDto> items = groupedRawPricelistItems.keySet()
									.stream()
									.sorted(Comparator.comparing(CommodityKey::getCommodityName))
									.map(commodityKey -> new ItemDto(commodityKey, 
															  		  groupedRawPricelistItems.get(commodityKey)
															  		  .stream()
															  		  .map(rawPricelistItemDto -> new PricelistItemDto(rawPricelistItemDto))
															  		  .collect(Collectors.toList())
											                         )
											
										)
									 .collect(Collectors.toList());
			
			//setting tax for each commodity based on commodity group which belong, which in turn have some tax value based on requestedTime
			items.forEach(itemDto -> {
				
				CommodityKey cKey = itemDto.getCommodity();
				
				cKey.setTaxRate(taxService.findTaxForCommodityGroup(Long.valueOf(requestedTime), cKey.getCommodityGroupId()));
				
			});
			
			response.setCode(200);
			response.setItems(items);
			response.setError(false);
			response.setMessage("odgovor");

//			response.setMessage(messageSource.getMessage("pricelist.items", null, new Locale("en")));

	
			
		} else {
			response.setCode(200);
			response.setItems(null);
			response.setError(true);
			//response.setMessage(messageSource.getMessage("pricelist.items.empty", null, new Locale("en")));
			response.setMessage("odgovor");
		}
				
		
		return response;
	}
}
