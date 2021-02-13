package sales.management.system.dtoResponse;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@Setter
@SuperBuilder
public class PricelistDetailResponse {
	
	List<PricelistDetailDto> details;
	private String message;
	private boolean error;
	private int code;

}
