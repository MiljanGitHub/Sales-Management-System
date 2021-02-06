package sales.management.system.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pricelist")
@Table(name = "pricelist")
@NoArgsConstructor
@Getter
@Setter
public class Pricelist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pricelist_id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false)
	private String validFrom;

	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
//	@OneToMany(mappedBy = "commodity")
//	private Set<PricelistItem> priceListItems;
	
	
	@ManyToMany(mappedBy = "pricelists",cascade = CascadeType.ALL)
	Set<PricelistItem> pricelistItems = new HashSet<>();
	

}
