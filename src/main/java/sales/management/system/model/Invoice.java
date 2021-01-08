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
import sales.management.system.model.enums.EInvoiceStatus;

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

	@Column(nullable = false)
	private int invoiceNumber;

	@Column(nullable = false)
	private LocalDateTime invoiceDate;

	@Column(nullable = false)
	private LocalDateTime currencyDate;

	@Column(nullable = false,precision = 2,length = 15)
	private double basisTotal;

	@Column(nullable = false,precision = 2,length = 15)
	private double taxTotal;

	@Column(nullable = false,precision = 2,length = 15)
	private double totalAmmount;

	@Column(nullable = false)
	private EInvoiceStatus status;


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
