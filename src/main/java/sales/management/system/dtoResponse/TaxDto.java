package sales.management.system.dtoResponse;

import sales.management.system.model.Tax;

public class TaxDto {

    private int id;
    private double percentage;
    private String validFrom;

    public TaxDto(Tax tax){
        this.id=tax.getId();
        this.percentage=tax.getPercentage();
        this.validFrom=tax.getValidFrom();
    }



}
