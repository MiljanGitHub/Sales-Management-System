package sales.management.system.dtoResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@Setter
@SuperBuilder
public class InvoiceDto {
	
	private int id;
	private String invoiceNumber;
	private String invoiceDate;
	private double basisTotal;
	private double taxTotal;
	private double totalAmmount;
	private String status;
	private String bussinesPartner;
	private String urlJasperReport;
	
	public InvoiceDto(int id, String invoiceNumber, String invoiceDate, double basisTotal, double taxTotal,
			double totalAmmount, String status, String bussinesPartner, String urlJasperReport) {
		super();
		this.id = id;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.basisTotal = basisTotal;
		this.taxTotal = taxTotal;
		this.totalAmmount = totalAmmount;
		this.status = status;
		this.bussinesPartner = bussinesPartner;
		this.urlJasperReport = urlJasperReport;
	}
	
	
	
}
