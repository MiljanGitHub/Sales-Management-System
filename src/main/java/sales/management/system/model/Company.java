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

@Entity(name = "Company")
@Table(name = "company")
@NoArgsConstructor
@Data
@Setter
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_id", unique = true, nullable = false)
	private int id;
	
	@OneToMany(mappedBy = "company")
	private Set<Invoice> invoices;
	
	@OneToMany(mappedBy = "company")
	private Set<BussinesPartner> partners;
	
	@OneToMany(mappedBy = "company")
	private Set<BussinesYear> years;
	
	@OneToMany(mappedBy = "company")
	private Set<Commodity> commodities;
	
	@OneToMany(mappedBy = "company")
	private Set<Pricelist> pricelists;
	
	@OneToMany(mappedBy = "company")
	private Set<CommodityGroupe> commodityGroups;
	

}
