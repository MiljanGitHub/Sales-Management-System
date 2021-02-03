package sales.management.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Data
@Setter
public class RawPricelistItem {
	

	private int unitId;
	private String unitShortNam;
	private String unitLongName;
	private double price;
	private CommodityKey commodityKey;
	
	
	
	public RawPricelistItem(int commodityId, String commodityName, int unitId, String unitShortNam, String unitLongName, double price) {
		super();

		this.unitId = unitId;
		this.unitShortNam = unitShortNam;
		this.unitLongName = unitLongName;
		this.price = price;
		this.commodityKey = new CommodityKey(commodityId, commodityName);
	}



	public int getUnitId() {
		return unitId;
	}



	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}



	public String getUnitShortNam() {
		return unitShortNam;
	}



	public void setUnitShortNam(String unitShortNam) {
		this.unitShortNam = unitShortNam;
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



	public CommodityKey getCommodityKey() {
		return commodityKey;
	}


	public void setCommodityKey(CommodityKey commodityKey) {
		this.commodityKey = commodityKey;
	}
	
	
}
