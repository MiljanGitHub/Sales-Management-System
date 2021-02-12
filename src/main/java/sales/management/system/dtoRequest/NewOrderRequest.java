package sales.management.system.dtoRequest;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@Setter
@SuperBuilder
public class NewOrderRequest {
	
	private int bussinesPartnerId;
	private double totalBasis; //ukupna osnovica
	private double taxAmount; //ukupno za porez
	private double total; //total total
	private List<OrderItem> items;

}
