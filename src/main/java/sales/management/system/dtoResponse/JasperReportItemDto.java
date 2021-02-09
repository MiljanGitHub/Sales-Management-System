package sales.management.system.dtoResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sales.management.system.model.InvoiceItem;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class JasperReportItemDto {


    private int number;
    private String item;
    private String unit;
    private int ammount;
    private Double price;
    private Double taxPercentage;
    private Double tax;
    private Double total;

    public JasperReportItemDto(InvoiceItem invoiceItem){
        this.number=invoiceItem.getId();
        this.item=invoiceItem.getCommodity().getName();
        this.unit=invoiceItem.getCommodity().getUnit().getName();
        this.ammount=invoiceItem.getAmount();
        this.price=invoiceItem.getUnitPrice();
        this.taxPercentage=invoiceItem.getTaxPercentage();
        this.tax=invoiceItem.getTaxAmount();
        this.total=invoiceItem.getTotal();


    }

}
