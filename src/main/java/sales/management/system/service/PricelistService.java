package sales.management.system.service;

import java.util.List;

import sales.management.system.dtoRequest.PriceListItemDTORequest;

public interface PricelistService {
	 boolean createNew(String validFrom, List<PriceListItemDTORequest> pricelistItemsDto);
	
}	
