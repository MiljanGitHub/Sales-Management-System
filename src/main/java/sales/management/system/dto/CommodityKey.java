package sales.management.system.dto;

public class CommodityKey {
	
	private int commodityId;
	private String commodityName;
	
	public CommodityKey(int commodityId, String commodityName) {
		super();
		this.commodityId = commodityId;
		this.commodityName = commodityName;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commodityId;
		result = prime * result + ((commodityName == null) ? 0 : commodityName.hashCode());
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
		if (commodityId != other.commodityId)
			return false;
		if (commodityName == null) {
			if (other.commodityName != null)
				return false;
		} else if (!commodityName.equals(other.commodityName))
			return false;
		return true;
	}
	
	

}
