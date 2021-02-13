package sales.management.system.dtoResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@Setter
@SuperBuilder
public class PricelistDto {
	
	private int pricelistId;
	private String validFrom;
	
	
	public PricelistDto(int pricelistId, String validFrom) {
		super();
		this.pricelistId = pricelistId;
		this.validFrom = validFrom;
	}
	
	

}
