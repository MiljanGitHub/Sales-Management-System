package sales.management.system.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import sales.management.system.dtoResponse.RawTax;
import sales.management.system.repository.TaxRepository;
import sales.management.system.service.TaxService;

@Service
@Validated
public class TaxServiceImpl implements TaxService{
	
	private TaxRepository repository;
	
	@Inject
	public TaxServiceImpl(final TaxRepository repository) {
		this.repository = repository; 
	}

	@Override
	public int findTaxForCommodityGroup(long requestedTime, int commodityGroupId) {
		// TODO Auto-generated method stub
		return repository.findTaxRateForCommodityGroup(requestedTime, commodityGroupId);
	}

	@Override
	public RawTax findRawTaxValuesPerCommodityGroup(int commodityGroupId, long requestedTime) {
		return repository.findRawTaxValuesPerCommodityGroup(commodityGroupId, requestedTime);
	}

}
