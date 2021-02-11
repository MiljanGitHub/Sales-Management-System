package sales.management.system.dtoResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@Setter
@SuperBuilder
@NoArgsConstructor
public class RawTax {
	
	private int commodityGroupId;
	private double tax;
	
	public RawTax(int commodityGroupId, double tax) {
		super();
		this.commodityGroupId = commodityGroupId;
		this.tax = tax;
	}
	
	
}
