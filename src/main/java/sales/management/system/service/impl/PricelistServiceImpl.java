package sales.management.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sales.management.system.dtoRequest.PriceListItemDTORequest;
import sales.management.system.model.Commodity;
import sales.management.system.model.Company;
import sales.management.system.model.Pricelist;
import sales.management.system.model.PricelistItem;
import sales.management.system.repository.CopmanyRepository;
import sales.management.system.repository.PricelistRepository;
import sales.management.system.service.PricelistItemService;
import sales.management.system.service.PricelistService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PricelistServiceImpl implements PricelistService{

    @Autowired
    private PricelistItemService pricelistItemService;

    @Autowired
    private PricelistRepository pricelistRepository;

    @Autowired
    private CopmanyRepository copmanyRepository;

    @Transactional
    @Override
    public boolean createNew(String validFrom, List<PriceListItemDTORequest> pricelistItemsDto) {
        try {
            Pricelist pricelist = new Pricelist();

            pricelist.setCompany(copmanyRepository.findById(1).get());//nesto drugo sem new company treba
            pricelist.setValidFrom(validFrom);
//            pricelistRepository.save(pricelist);
//
            for (PriceListItemDTORequest p : pricelistItemsDto) {
                PricelistItem pricelistItem = pricelistItemService.createNewPriceListItem(p.getPrice(),p.getId(),pricelist);
                pricelist.getPricelistItems().add(pricelistItem);

            }


             pricelistRepository.save(pricelist);
             return true;


        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
