package sales.management.system.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import sales.management.system.dtoRequest.PriceListCopyDto;
import sales.management.system.dtoRequest.PriceListItemDTORequest;
import sales.management.system.dtoResponse.CommodityDto;
import sales.management.system.dtoResponse.DataForPriceListCopyDto;
import sales.management.system.model.Commodity;
import sales.management.system.dtoResponse.CommodityKey;
import sales.management.system.dtoResponse.ItemDto;
import sales.management.system.dtoResponse.PricelistItemDto;
import sales.management.system.dtoResponse.PricelistItemResponse;
import sales.management.system.dtoResponse.RawPricelistItem;
import sales.management.system.model.Pricelist;
import sales.management.system.model.PricelistItem;
import sales.management.system.repository.CommodityRepository;
import sales.management.system.repository.CopmanyRepository;
import sales.management.system.repository.PricelistRepository;
import sales.management.system.service.PricelistItemService;
import sales.management.system.service.PricelistService;
import sales.management.system.service.TaxService;

@Service
public class PricelistServiceImpl implements PricelistService{

    @Autowired
    private PricelistItemService pricelistItemService;

    @Autowired
    private PricelistRepository pricelistRepository;

    @Autowired
    private CopmanyRepository copmanyRepository;
 	


    @Autowired
    private CommodityRepository commodityRepository;

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
                System.out.println("Hello");
                System.out.println(p.getPrice());
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

//    Transactional
    @Override
    public DataForPriceListCopyDto makeCopy(PriceListCopyDto priceListCopyDto) {
        try {
            DataForPriceListCopyDto dataForPriceListCopyDto = new DataForPriceListCopyDto();
            ArrayList<Commodity> commoditiesNotInPriceList = commodityRepository.getCommoditiesNotInPriceList(priceListCopyDto.getPriceListId());
            for (Commodity c : commoditiesNotInPriceList) {
                CommodityDto commodityDto = new CommodityDto(c);
                dataForPriceListCopyDto.getCommodityDtos().add(commodityDto);
            }
            Pricelist pricelistToCopy = pricelistRepository.findById(priceListCopyDto.getPriceListId()).get();
            for (PricelistItem p : pricelistToCopy.getPricelistItems()) {
                PriceListItemDTORequest priceListItemToReturn = new PriceListItemDTORequest(p, priceListCopyDto.isIncrease(), priceListCopyDto.getPercentage());
                dataForPriceListCopyDto.getPriceListItems().add(priceListItemToReturn);
            }
            return dataForPriceListCopyDto;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    
 
	

}
