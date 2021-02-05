package sales.management.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sales.management.system.model.Commodity;
import sales.management.system.repository.CommodityRepository;
import sales.management.system.repository.PricelistItemRepository;
import sales.management.system.service.CommodityService;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService{

    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public List<Commodity> getAllCommodities() {
        return commodityRepository.findAll();
    }
}
