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

@Entity(name = "CommodityGroupe")
@Table(name = "commodity_groupe")
@NoArgsConstructor
@Getter
@Setter
public class CommodityGroupe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="commodity_groupe_id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false,length = 40)
	private String name;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;

	@JsonIgnore
	@OneToMany(mappedBy = "commodityGroupe")
	private Set<Commodity> commodities;
	

	@OneToMany(mappedBy = "commodityGroup")
	private Set<Tax> taxes;

}
