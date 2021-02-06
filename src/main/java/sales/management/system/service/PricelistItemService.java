package sales.management.system.service;

import java.util.List;

import sales.management.system.dto.RawPricelistItem;
import sales.management.system.model.Pricelist;
import sales.management.system.model.PricelistItem;

public interface PricelistItemService {
	
	List<RawPricelistItem> findPricelistItems(Long requestTime);
	PricelistItem createNewPriceListItem(Double price, int ComodityId, Pricelist pricelist);
}
