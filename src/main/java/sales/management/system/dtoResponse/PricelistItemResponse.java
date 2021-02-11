package sales.management.system.dtoResponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@Setter
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class PricelistItemResponse {
	
	List<RawPricelistItem> commodities;
	private String message;
	private boolean error;
	private int code;
	

}
