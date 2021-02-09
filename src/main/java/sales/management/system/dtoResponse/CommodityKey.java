package sales.management.system.dtoResponse;

public class CommodityKey {
	
	private int commodityId;
	private String commodityName;
	private int commodityGroupId;
	private String description;
	private double taxRate;
	

	public CommodityKey(int commodityId, String commodityName, int commodityGroupId,  String description) {
		super();
		this.commodityId = commodityId;
		this.commodityName = commodityName;
		this.commodityGroupId = commodityGroupId;
		this.description = description;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public int getCommodityGroupId() {
		return commodityGroupId;
	}

	public void setCommodityGroupId(int commodityGroupId) {
		this.commodityGroupId = commodityGroupId;
	}
	
	

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commodityGroupId;
		result = prime * result + commodityId;
		result = prime * result + ((commodityName == null) ? 0 : commodityName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(taxRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommodityKey other = (CommodityKey) obj;
		if (commodityGroupId != other.commodityGroupId)
			return false;
		if (commodityId != other.commodityId)
			return false;
		if (commodityName == null) {
			if (other.commodityName != null)
				return false;
		} else if (!commodityName.equals(other.commodityName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(taxRate) != Double.doubleToLongBits(other.taxRate))
			return false;
		return true;
	}

	

	
	
	

}
