package sales.management.system.service;

import java.util.List;

import sales.management.system.dtoRequest.PriceListCopyDto;
import sales.management.system.dtoRequest.PriceListItemDTORequest;
import sales.management.system.dtoResponse.DataForPriceListCopyDto;

public interface PricelistService {
	
	 boolean createNew(String validFrom, List<PriceListItemDTORequest> pricelistItemsDto);
<<<<<<< HEAD

	 DataForPriceListCopyDto makeCopy(PriceListCopyDto priceListCopyDto);
=======
	 
	 
	
>>>>>>> e5e09d8fd82f56d1188c06aef97ee7855e4cf953
}	
