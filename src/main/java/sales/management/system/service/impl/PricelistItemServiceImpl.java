package sales.management.system.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import sales.management.system.dto.RawPricelistItem;
import sales.management.system.repository.PricelistItemRepository;
import sales.management.system.service.PricelistItemService;

@Service
@Validated
public class PricelistItemServiceImpl implements PricelistItemService{
	
	private final PricelistItemRepository repository;
	
	@Inject
	public PricelistItemServiceImpl(final PricelistItemRepository repository) {
		this.repository = repository;
	}

	public List<RawPricelistItem> findPricelistItems(Long requestTime) {
		// TODO Auto-generated method stub
		return repository.findPricelistItems(requestTime);
	}

}
