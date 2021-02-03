package sales.management.system.dto;

import java.util.List;

public class ItemDto {
	
	private CommodityKey commodity;
	private List<PricelistItemDto> priceListItemsDto;
	
	public ItemDto(CommodityKey commodity, List<PricelistItemDto> priceListItemsDto) {
		super();
		this.commodity = commodity;
		this.priceListItemsDto = priceListItemsDto;
	}
	
	public CommodityKey getCommodity() {
		return commodity;
	}
	public void setCommodity(CommodityKey commodity) {
		this.commodity = commodity;
	}
	public List<PricelistItemDto> getPriceListItemsDto() {
		return priceListItemsDto;
	}
	public void setPriceListItemsDto(List<PricelistItemDto> priceListItemsDto) {
		this.priceListItemsDto = priceListItemsDto;
	}
	
	
}
