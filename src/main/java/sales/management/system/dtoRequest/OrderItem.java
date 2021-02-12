package sales.management.system.dtoRequest;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Data
@Setter
@SuperBuilder
@RequiredArgsConstructor
public class OrderItem {

	private int commodityId;
	private int amount;
	private double unitPrice;
	private double taxPercentage;
	private double taxAmount;
	private double total;

}
