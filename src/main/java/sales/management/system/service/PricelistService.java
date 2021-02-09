package sales.management.system.service;

import java.util.List;

import sales.management.system.dtoRequest.PriceListCopyDto;
import sales.management.system.dtoRequest.PriceListItemDTORequest;
import sales.management.system.dtoResponse.DataForPriceListCopyDto;

public interface PricelistService {
	 boolean createNew(String validFrom, List<PriceListItemDTORequest> pricelistItemsDto);

	 DataForPriceListCopyDto makeCopy(PriceListCopyDto priceListCopyDto);
}	
