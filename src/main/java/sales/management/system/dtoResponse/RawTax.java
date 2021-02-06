package sales.management.system.dtoResponse;

public class RawTax {
	

	private double taxRate;
	
	public RawTax(double taxRate) {
		super();

		this.taxRate = taxRate;
	}


	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	
	

}
