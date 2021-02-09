package sales.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import sales.management.system.dtoRequest.PriceListCopyDto;
import sales.management.system.dtoRequest.PriceListDTORequest;
import sales.management.system.dtoResponse.CommodityDto;
import sales.management.system.dtoResponse.DataForPriceListCopyDto;
=======
import sales.management.system.controller.impl.PriceListControllerImpl;
import sales.management.system.dtoRequest.PriceListDTORequest;
import sales.management.system.dtoResponse.PricelistItemResponse;
>>>>>>> e5e09d8fd82f56d1188c06aef97ee7855e4cf953
import sales.management.system.repository.CommodityRepository;
import sales.management.system.service.PricelistService;

import java.util.List;

@RestController
@RequestMapping("priceList/")
public class PriceListController {

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    PricelistService pricelistService;
    
    @Autowired
    private PriceListControllerImpl pricelistControllerImpl;

    @PostMapping(value = "create")
    public ResponseEntity postController(@RequestBody PriceListDTORequest priceListDTORequest) {
        try{

            pricelistService.createNew(priceListDTORequest.getDate(),priceListDTORequest.getPriceListItems());
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
	
	@RequestMapping(value = "pricelistItems/{requestedTime}", method = RequestMethod.GET) 
	public PricelistItemResponse getPricelistItems(@PathVariable String requestedTime) {
		
		PricelistItemResponse response = pricelistControllerImpl.getPricelistItems(requestedTime);
		
		return response;
	}

    @PostMapping(value = "copy")
    public ResponseEntity makeCopy(@RequestBody PriceListCopyDto priceListCopyDto) {
        try{
            DataForPriceListCopyDto dataForCopy=pricelistService.makeCopy(priceListCopyDto);

            return dataForCopy!=null ?  new ResponseEntity<DataForPriceListCopyDto>(dataForCopy, HttpStatus.OK) :  ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);


        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
