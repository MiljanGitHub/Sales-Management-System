package sales.management.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Item")
@Table(name = "item")
@NoArgsConstructor
@Data
@Setter
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id", unique = true, nullable = false)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="invoice_id")
	private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name="commodity_id")
	private Commodity commodity;

}
