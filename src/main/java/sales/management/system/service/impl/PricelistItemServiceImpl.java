package sales.management.system.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import sales.management.system.dtoResponse.RawPricelistItem;
import sales.management.system.model.Commodity;
import sales.management.system.model.Pricelist;
import sales.management.system.model.PricelistItem;
import sales.management.system.repository.CommodityRepository;
import sales.management.system.repository.PricelistItemRepository;
import sales.management.system.service.PricelistItemService;

@Service
@Validated
public class PricelistItemServiceImpl implements PricelistItemService{
	
	private final PricelistItemRepository repository;

	@Autowired
	private CommodityRepository commodityRepository;
	
	@Inject
	public PricelistItemServiceImpl(final PricelistItemRepository repository) {
		this.repository = repository;
	}

	public List<RawPricelistItem> findPricelistItems(Long requestTime) {
		// TODO Auto-generated method stub
		return repository.findPricelistItems(requestTime);
	}

	@Transactional
	@Override
	public PricelistItem createNewPriceListItem(Double price, int ComodityId, Pricelist pricelist) {

		Commodity commodity=commodityRepository.findById(ComodityId).get();
		System.out.println("COMMMMMMMMM");
		PricelistItem pricelistItem =new PricelistItem();
		pricelistItem.setPrice(price);
		pricelistItem.getPricelists().add(pricelist);// ovde je problem
		pricelistItem.setCommodity(commodity);

		return pricelistItem;
	}

}
