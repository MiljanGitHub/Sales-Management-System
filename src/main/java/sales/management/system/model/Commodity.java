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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Commodity")
@Table(name = "commodity")
@NoArgsConstructor
@SuperBuilder
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

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="unit_id")
	private Unit unit;

	@JsonIgnore
	@OneToMany(mappedBy = "commodity")
	private Set<InvoiceItem> items;

	@JsonIgnore
	@OneToMany(mappedBy = "commodity")
	private Set<PricelistItem> pricelistItems;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="commodity_group_id")
	private CommodityGroupe commodityGroupe;
	
	public void addInvoiceItem(InvoiceItem item) {
		//this.items.add(item);
		item.setCommodity(this);
	}
	
}
