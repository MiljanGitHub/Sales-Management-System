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
import sales.management.system.dtoRequest.PriceListCopyDto;
import sales.management.system.dtoRequest.PriceListDTORequest;
import sales.management.system.dtoResponse.DataForPriceListCopyDto;
import sales.management.system.dtoResponse.PricelistDetailResponse;
import sales.management.system.dtoResponse.PricelistItemResponse;
import sales.management.system.dtoResponse.PricelistResponse;
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
    public ResponseEntity<?> postController(@RequestBody PriceListDTORequest priceListDTORequest) {
        try{

            pricelistService.createNew(priceListDTORequest.getDate(),priceListDTORequest.getPriceListItems());
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    /*
     * Service return price-list items and their associated date when Business Partner is creating new Order
     */
	@RequestMapping(value = "pricelistItems/{requestedTime}", method = RequestMethod.GET) 
	public PricelistItemResponse getPricelistItems(@PathVariable String requestedTime) {
		
		PricelistItemResponse response = pricelistControllerImpl.getPricelistItems(requestedTime);
		
		return response;
	}

    @PostMapping(value = "copy")
    public ResponseEntity<?> makeCopy(@RequestBody PriceListCopyDto priceListCopyDto) {
        try{
            DataForPriceListCopyDto dataForCopy=pricelistService.makeCopy(priceListCopyDto);

            return dataForCopy!=null ?  new ResponseEntity<DataForPriceListCopyDto>(dataForCopy, HttpStatus.OK) :  ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);


        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    /*
     * Service return price-lists with their valid-from dates as well as PK as an identifiers.
     */
	@RequestMapping(value = "", method = RequestMethod.GET) 
	public PricelistResponse getPricelists() {
		
		PricelistResponse response = pricelistControllerImpl.getPricelists();
		
		return response;
		
	}
    
    /*
     * Service return price-list items and their associated date for price-list overview
     */
	@RequestMapping(value = "details/{pricelistId}", method = RequestMethod.GET) 
	public PricelistDetailResponse getPricelistsDetails(@PathVariable Integer pricelistId) {
		
		PricelistDetailResponse response =  pricelistControllerImpl.getPricelistsDetails(pricelistId);
		
		return response;
	}
}
