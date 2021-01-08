package sales.management.system.model;


import java.time.LocalDateTime;
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

@Entity(name = "Pricelist")
@Table(name = "pricelist")
@NoArgsConstructor
@Data
@Setter
public class Pricelist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pricelist_id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false)
	private LocalDateTime validFrom;

	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	@OneToMany(mappedBy = "commodity")
	private Set<PricelistItem> priceListItems;

}
