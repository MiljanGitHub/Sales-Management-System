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
import sales.management.system.dtoRequest.OrderItem;

@Entity(name = "Item")
@Table(name = "item")
@NoArgsConstructor
@Data
@Setter
//@EqualsAndHashCode
public class InvoiceItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id", unique = true, nullable = false)
	private int id;

	@Column(name = "amount")
	private int amount;

	@Column(nullable = false,precision = 2,length = 15)
	private double discount; //this is 'rabat' from UML diagram

	@Column(nullable = false,precision = 2,length = 15)
	private double unitPrice;

	@Column(nullable = false,precision = 2,length = 15)
	private double basis; //(amount * unitPrice) * (discount/100) 

	@Column(nullable = false,precision = 2,length = 15)
	private double taxPercentage; 

	@Column(nullable = false,precision = 2,length = 15)
	private double taxAmount; //basis * (taxPercentage / 100)

	@Column(nullable = false,precision = 2,length = 15)
	private double total;

	
	@ManyToOne
	@JoinColumn(name="invoice_id")
	private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name="commodity_id")
	private Commodity commodity;
	
	
	public InvoiceItem(OrderItem item, Invoice newInvoice) {
		this.invoice = newInvoice;
		this.amount = item.getAmount();
		this.basis = item.getAmount() * item.getUnitPrice();
		this.unitPrice = item.getUnitPrice();
		this.taxPercentage = item.getTaxPercentage();
		this.taxAmount = item.getTaxAmount();
		this.total = item.getTotal();
	}
	
	public InvoiceItem(OrderItem item) {
		this.amount = item.getAmount();
		this.basis = item.getAmount() * item.getUnitPrice();
		this.unitPrice = item.getUnitPrice();
		this.taxPercentage = item.getTaxPercentage();
		this.taxAmount = item.getTaxAmount();
		this.total = item.getTotal();
	}
	
	public void addOrder(Invoice newInvoice) {
		newInvoice.getItems().add(this);
		//this.setInvoice(newInvoice);
	}

}
