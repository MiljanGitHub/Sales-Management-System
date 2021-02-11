package sales.management.system.service;

import sales.management.system.dtoResponse.RawTax;

public interface TaxService {
	
	int findTaxForCommodityGroup(long requestedTime, int commodityGroupId);
	RawTax findRawTaxValuesPerCommodityGroup(int commodityGroupId, long requestedTime);
}
