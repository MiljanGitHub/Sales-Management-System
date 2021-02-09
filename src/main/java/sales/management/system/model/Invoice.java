package sales.management.system.model;


import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sales.management.system.model.enums.EInvoiceStatus;

@Entity(name = "Invoice")
@Table(name = "invoice")
@NoArgsConstructor
@Data
@Setter
//@EqualsAndHashCode
public class Invoice {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="invoice_id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false)
	private String invoiceNumber;

	@Column(nullable = false)
	private String invoiceDate;

	@Column(nullable = false)
	private String currencyDate;

	@Column(nullable = false,precision = 2,length = 15)
	private double basisTotal;

	@Column(nullable = false,precision = 2,length = 15)
	private double taxTotal;

	@Column(nullable = false,precision = 2,length = 15)
	private double totalAmmount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EInvoiceStatus status;


	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	
	@ManyToOne
	@JoinColumn(name="bussines_partner_id")
	private BussinesPartner partner;
	
	
	@OneToMany(mappedBy = "invoice")
	private List<InvoiceItem> items;

}
