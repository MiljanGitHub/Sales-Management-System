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
import sales.management.system.model.enums.EBussinesPartnerType;

@Entity(name = "BussinesPartner")
@Table(name = "bussines_partner")
@NoArgsConstructor
@Data
@Setter
public class BussinesPartner {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bussines_partner_id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false,length = 100)
	private String name;

	@Column(nullable = false,length = 50)
	private String address;

	@Column(length = 20)
	private String phoneNumber;

	@Column(length = 29)
	private String fax;

	@Column(length = 20)
	private String email;

	@Column(nullable = false)
	private EBussinesPartnerType type;

	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	@OneToMany(mappedBy = "partner")
	private Set<Invoice> invoices;

}
