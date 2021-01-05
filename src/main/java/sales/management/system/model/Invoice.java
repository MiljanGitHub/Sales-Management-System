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

@Entity(name = "Invoice")
@Table(name = "invoice")
@NoArgsConstructor
@Data
@Setter
public class Invoice {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="invoice_id", unique = true, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	
	@ManyToOne
	@JoinColumn(name="bussines_year_id")
	private BussinesYear year;
	
	@ManyToOne
	@JoinColumn(name="bussines_partner_id")
	private BussinesPartner partner;
	
	
	@OneToMany(mappedBy = "invoice")
	private Set<Item> items;

}
