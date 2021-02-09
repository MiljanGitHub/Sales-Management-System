package sales.management.system.dtoResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@Data
@Setter
@SuperBuilder
public class RawPricelistItem {
	

	private int unitId;
	private String unitShortName;
	private String unitLongName;
	private double price;
	private CommodityKey commodityKey;
	
	
	
	public RawPricelistItem(int commodityId, String commodityName, String commodityDescription, int commodityGroupId, int unitId, String unitShortName, String unitLongName, double price) {
		super();

		this.unitId = unitId;
		this.unitShortName = unitShortName;
		this.unitLongName = unitLongName;
		this.price = price;
		this.commodityKey = new CommodityKey(commodityId, commodityName, commodityGroupId, commodityDescription);
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



	public CommodityKey getCommodityKey() {
		return commodityKey;
	}


	public void setCommodityKey(CommodityKey commodityKey) {
		this.commodityKey = commodityKey;
	}
	
	
}
