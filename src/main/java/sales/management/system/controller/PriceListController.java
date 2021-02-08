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

import sales.management.system.controller.impl.PriceListControllerImpl;
import sales.management.system.dtoRequest.PriceListDTORequest;
import sales.management.system.dtoResponse.PricelistItemResponse;
import sales.management.system.repository.CommodityRepository;
import sales.management.system.service.PricelistService;

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
    public ResponseEntity<HttpStatus> postController(@RequestBody PriceListDTORequest priceListDTORequest) {
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




}
