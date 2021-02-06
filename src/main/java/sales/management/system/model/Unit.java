package sales.management.system.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Unit")
@Table(name = "unit")
@NoArgsConstructor
@Data
@Setter
@SuperBuilder
public class Unit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="unit_id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false,length = 20)
	private String name;

	@Column(nullable = false,length = 10)
	private String shortName;
	
	@OneToMany(mappedBy = "unit")
	private Set<PricelistItem> pricelistItems;
}
