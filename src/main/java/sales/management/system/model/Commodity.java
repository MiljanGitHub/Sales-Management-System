package sales.management.system.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Commodity")
@Table(name = "commodity")
@NoArgsConstructor
@Data
@Setter
public class Commodity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="commodity_id", unique = true, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	@OneToMany(mappedBy = "commodity")
	private Set<Item> items;
	
	@OneToMany(mappedBy = "commodity")
	private Set<PricelistItem> pricelistItems;
	
	@ManyToOne
	@JoinColumn(name="unit_id")
	private Unit unit;
	
	@ManyToOne
	@JoinColumn(name="commodity_group_id")
	private CommodityGroupe commodityGroupe;

}
