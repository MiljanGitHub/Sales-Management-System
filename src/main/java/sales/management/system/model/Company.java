package sales.management.system.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Company")
@Table(name = "company")
@NoArgsConstructor
@Getter
@Setter
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false,length = 100)
	private String name;

	@Column(nullable = false,length = 50)
	private String address;

	@Column(length = 20)
	private String phoneNumber;

	@Column(length = 29)
	private String fax;

	@OneToMany(mappedBy = "company")
	private Set<Invoice> invoices;
	
	@OneToMany(mappedBy = "company")
	private List<BussinesPartner> partners;
	
	@OneToMany(mappedBy = "company")
	private Set<Commodity> commodities;
	
	@OneToMany(mappedBy = "company")
	private Set<Pricelist> priceLists;
	
	@OneToMany(mappedBy = "company")
	private Set<CommodityGroupe> commodityGroups;
	
	public void addInvoice(Invoice invoice) {
		invoice.setCompany(this);
	}
	
	public void addBussinesPartner(BussinesPartner bussinesPartner) {
		bussinesPartner.setCompany(this);
		partners.add(bussinesPartner);
		
	}

}
