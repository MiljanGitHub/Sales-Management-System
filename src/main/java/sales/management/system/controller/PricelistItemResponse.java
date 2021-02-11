package sales.management.system.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sales.management.system.dtoResponse.RawPricelistItem;

@Data
@Setter
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class PricelistItemResponse {
	
	List<RawPricelistItem> pricelistItems;
	private String message;
	private boolean error;
	private int code;
	

}
