package sales.management.system.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sales.management.system.model.enums.EInvoiceStatus;

@Entity(name = "Invoice")
@Table(name = "invoice")
@NoArgsConstructor
@Getter
@Setter
@SqlResultSetMappings({ //

	@SqlResultSetMapping(name = "findInvoicesByDateMapping",
			
			classes = {@ConstructorResult(targetClass=sales.management.system.dtoResponse.InvoiceDto.class,
			columns = {@ColumnResult(name="id", type=Integer.class),
					   @ColumnResult(name="invoiceNumber", type=String.class),
					   @ColumnResult(name="invoiceDate", type=String.class), 
					   @ColumnResult(name="basisTotal", type=Double.class),
					   @ColumnResult(name="taxTotal", type=Double.class),
					   @ColumnResult(name="totalAmmount", type=Double.class),
					   @ColumnResult(name="status", type=String.class),
					   @ColumnResult(name="bussinesPartner", type=String.class),
					   @ColumnResult(name="urlJasperReport", type=String.class),
	
			})} )
	 })
@NamedNativeQueries(value = {
		
		@NamedNativeQuery(name = "findInvoicesByDate", query = ""
				+ "SELECT i.invoice_id AS id, i.invoice_number AS invoiceNumber, i.invoice_date AS invoiceDate, i.basis_total AS basisTotal, i.tax_total AS taxTotal, i.total_ammount AS totalAmmount, i.status AS status, bp.name AS bussinesPartner, i.url AS urlJasperReport "
				+ "FROM invoice i "
				+ "JOIN bussines_partner bp ON (bp.bussines_partner_id = i.bussines_partner_id)"
				+ "WHERE (i.invoice_date+0) >= :from AND (i.invoice_date+0) <= :to"
				, resultSetMapping = "findInvoicesByDateMapping")

	 })
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
	
	@Column(nullable = true, columnDefinition = "VARCHAR(500)")
	private String url;


	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	
	@ManyToOne
	@JoinColumn(name="bussines_partner_id")
	private BussinesPartner partner;
	
	
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private List<InvoiceItem> items;
	
	public void addInvoiceItem(InvoiceItem item) {
		item.setInvoice(this);
		this.items.add(item);
		
	}

}
