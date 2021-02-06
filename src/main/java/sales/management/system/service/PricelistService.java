package sales.management.system.service;

import sales.management.system.dtoRequest.PriceListItemDTORequest;
import sales.management.system.model.PricelistItem;

import java.util.List;

public interface PricelistService {
	 boolean createNew(String validFrom, List<PriceListItemDTORequest> pricelistItemsDto);
	
}	
