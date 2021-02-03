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

@Entity(name = "Tax")
@Table(name = "tax")
@NoArgsConstructor
@Data
@Setter
public class Tax {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tax_id", unique = true, nullable = false)
	private int id;

	@Column(precision = 2,length = 15) // 5 or 15 ?
	private double percentage;

	@Column(nullable = false)
	private String validFrom;
	
	//@OneToMany(mappedBy = "tax")
	@ManyToOne
	@JoinColumn(name="commodity_groupe_id")
	private CommodityGroupe commodityGroup;

}
