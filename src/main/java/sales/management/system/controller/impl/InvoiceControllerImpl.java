package sales.management.system.controller.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import sales.management.system.dto.CommodityKey;
import sales.management.system.dto.ItemDto;
import sales.management.system.dto.PricelistItemDto;
import sales.management.system.dto.PricelistItemResponse;
import sales.management.system.dto.RawPricelistItem;
import sales.management.system.service.PricelistItemService;
import sales.management.system.service.TaxService;

@Service
public class InvoiceControllerImpl {
	
	@Autowired
	private PricelistItemService pricelistItemService;
	
	@Autowired
	private TaxService taxService;
	
	@Autowired
	MessageSource messageSource;
	
	
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
			
			//TODO
			//Treba odrediti vrednost poreza za svaki commodity key
			items.forEach(itemDto -> {
				
				CommodityKey cKey = itemDto.getCommodity();
				
				cKey.setTaxRate(taxService.findTaxForCommodityGroup(Long.valueOf(requestedTime), cKey.getCommodityGroupId()));
				
			});
			
			
			
			response.setCode(200);
			response.setItems(items);
			//TODO
			//Treba setovati poruku is message.properties fajla
			response.setMessage("vracena cenovnik za izabrani datum");
	
			
		} else {
			System.out.println("za izabrani datum ne postoji validan cenovnik");
		}
				
		
		return response;
	}
	
}
