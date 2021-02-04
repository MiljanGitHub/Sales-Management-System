package sales.management.system.service;

public interface TaxService {
	
	int findTaxForCommodityGroup(long requestedTime, int commodityGroupId);
}
