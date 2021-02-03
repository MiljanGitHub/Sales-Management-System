package sales.management.system.service;

import java.util.List;

import sales.management.system.dto.RawPricelistItem;

public interface PricelistItemService {
	
	List<RawPricelistItem> findPricelistItems(Long requestTime);
}
