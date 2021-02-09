package sales.management.system.dtoRequest;

import sales.management.system.model.PricelistItem;

public class PriceListItemDTORequest {
        private int id;
        private String name;
        private String type;
        private Double price;

        public PriceListItemDTORequest(PricelistItem pricelistItem,Boolean increase,Double percentage){
            this.id=pricelistItem.getCommodity().getId();
            this.name=pricelistItem.getCommodity().getName();
            this.type=pricelistItem.getCommodity().isGoods() ? "commodity" : "service";
            this.price=getNewPrice(increase,percentage,pricelistItem.getPrice());

        }
        public PriceListItemDTORequest(){}
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    private double getNewPrice(Boolean increase,Double percentage,Double oldPrice){
//            Double newPrice=0.0;
        return increase ? oldPrice+oldPrice*percentage/100 : oldPrice-oldPrice*percentage/100;

    }
}
