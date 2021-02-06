package sales.management.system.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Commodity")
@Table(name = "commodity")
@NoArgsConstructor
@Setter
@Getter
public class Commodity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="commodity_id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false,length = 70)
	private String name;

	@Column(nullable = false,length = 100)
	private String description;

	@Column(name = "goods", nullable = true)
	private boolean goods;  // goods or service true for goods ?

	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	@ManyToOne
	@JoinColumn(name="unit_id")
	private Unit unit;
	
	@OneToMany(mappedBy = "commodity")
	private Set<Item> items;
	
	@OneToMany(mappedBy = "commodity")
	private Set<PricelistItem> pricelistItems;
	
	@ManyToOne
	@JoinColumn(name="commodity_group_id")
	private CommodityGroupe commodityGroupe;





}
