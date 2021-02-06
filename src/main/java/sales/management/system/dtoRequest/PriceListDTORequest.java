package sales.management.system.dtoRequest;

import java.util.List;

public class PriceListDTORequest {
    private String date;
    private List<PriceListItemDTORequest> priceListItems;

    public String getDate() {
        return date;
    }

    public List<PriceListItemDTORequest> getPriceListItems() {
        return priceListItems;
    }
}
