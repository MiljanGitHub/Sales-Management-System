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

	private int commodityId;
	private String description;
	private String name;
	private int unitId;
	private String unitName;
	private String unitShortName;
	private double price;
	private int commodityGroupId;
	private double tax;
	
	public RawPricelistItem(int commodityId, String description, String name, int unitId, String unitName, String unitShortName, double price, int commodityGroupId) {
		super();
		this.commodityId = commodityId;
		this.description = description;
		this.name = name;
		this.unitId = unitId;
		this.unitName = unitName;
		this.unitShortName = unitShortName;
		this.price = price;
		this.commodityGroupId = commodityGroupId;
	}
	
	
}
