package sales.management.system.dtoRequest;

public class PriceListCopyDto {
    private int priceListId;
    private double percentage;
    private boolean increase;// true=increase, false=decrease

    public int getPriceListId() {
        return priceListId;
    }

    public double getPercentage() {
        return percentage;
    }

    public boolean isIncrease() {
        return increase;
    }
}
