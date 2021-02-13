package sales.management.system.dtoResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@Setter
@SuperBuilder
public class PricelistDetailDto {


	private String item;
	private String description;
	private boolean goods;
	private double unitPrice;
	private String unitName;
	private String unitShortName;
	private int commodityId;
	private String commodityGroup;
	private int commodityGroupId;
	private double taxRate;
	
	
	public PricelistDetailDto(String item, String description, boolean goods, double unitPrice, String unitName, String unitShortName, int commodityId, String commodityGroup, int commodityGroupId) {
		super();
		this.item = item;
		this.description = description;
		this.goods = goods;
		this.unitPrice = unitPrice;
		this.unitName = unitName;
		this.unitShortName = unitShortName;
		this.commodityId = commodityId;
		this.commodityGroup = commodityGroup;
		this.commodityGroupId = commodityGroupId;
	}
	
	
	
	
}
