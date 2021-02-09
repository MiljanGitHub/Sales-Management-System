package sales.management.system.dtoResponse;

import sales.management.system.dtoRequest.PriceListItemDTORequest;

import java.util.ArrayList;

public class DataForPriceListCopyDto {
    private ArrayList<CommodityDto> commodityDtos;
    private ArrayList<PriceListItemDTORequest> priceListItems;// isti format koristim i da vratim kad pravim kopiiju

    public DataForPriceListCopyDto(){
        this.commodityDtos=new ArrayList<>();
        this.priceListItems=new ArrayList<>();
    }

    public ArrayList<CommodityDto> getCommodityDtos() {
        return commodityDtos;
    }

    public void setCommodityDtos(ArrayList<CommodityDto> commodityDtos) {
        this.commodityDtos = commodityDtos;
    }

    public ArrayList<PriceListItemDTORequest> getPriceListItems() {
        return priceListItems;
    }

    public void setPriceListItems(ArrayList<PriceListItemDTORequest> priceListItems) {
        this.priceListItems = priceListItems;
    }
}
