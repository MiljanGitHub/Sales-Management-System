package sales.management.system.dto;

public class PricelistItemDto {
	
	private int unitId;
	private String unitShortName;
	private String unitLongName;
	private double price;
	
	
	public PricelistItemDto(RawPricelistItem rawPricelistItem) {
		this.unitId = rawPricelistItem.getUnitId();
		this.unitShortName = rawPricelistItem.getUnitShortName();
		this.unitLongName = rawPricelistItem.getUnitLongName();
		this.price = rawPricelistItem.getPrice();
	}
	
	
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getUnitShortName() {
		return unitShortName;
	}
	public void setUnitShortName(String unitShortName) {
		this.unitShortName = unitShortName;
	}
	public String getUnitLongName() {
		return unitLongName;
	}
	public void setUnitLongName(String unitLongName) {
		this.unitLongName = unitLongName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
