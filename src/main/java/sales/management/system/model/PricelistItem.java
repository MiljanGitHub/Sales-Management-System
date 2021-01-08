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

@Entity(name = "PricelistItem")
@Table(name = "pricelist_item")
@NoArgsConstructor
@Data
@Setter
public class PricelistItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pricelistitem_id", unique = true, nullable = false)
	private int id;

	@Column(precision = 2,nullable = false)
	private double price;

	@ManyToOne
	@JoinColumn(name="commodity_id")
	private Commodity commodity;
	
	@ManyToOne
	@JoinColumn(name="pricelist_id")
	private Pricelist pricelist;

}
