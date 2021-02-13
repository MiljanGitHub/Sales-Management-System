package sales.management.system.service;

import java.util.List;

import sales.management.system.dtoRequest.PriceListCopyDto;
import sales.management.system.dtoRequest.PriceListItemDTORequest;
import sales.management.system.dtoResponse.DataForPriceListCopyDto;
import sales.management.system.dtoResponse.PricelistDetailDto;
import sales.management.system.dtoResponse.PricelistDto;
import sales.management.system.model.Company;
import sales.management.system.model.Pricelist;

public interface PricelistService {
	
	 boolean createNew(String validFrom, List<PriceListItemDTORequest> pricelistItemsDto);

	 DataForPriceListCopyDto makeCopy(PriceListCopyDto priceListCopyDto);

	 List<PricelistDto> findPricelistDto(Company company);
	 
	 List<PricelistDetailDto> findPricelistItemDetails(int pricelistId);
	 
	 Pricelist findById(int pricelistId);
	 
	
}
