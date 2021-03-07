package sales.management.system.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sales.management.system.dtoRequest.NewCommodityDto;
import sales.management.system.dtoResponse.CommodityDto;
import sales.management.system.dtoResponse.GroupDto;
import sales.management.system.model.Commodity;
import sales.management.system.model.CommodityGroupe;
import sales.management.system.model.Tax;
import sales.management.system.model.Unit;
import sales.management.system.service.CommodityService;

@RestController
@RequestMapping("comodities/")
public class ComodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping(value = "getAll")
    public ResponseEntity<List<CommodityDto>> getAccounts() {
        List<Commodity> comodities = commodityService.getAllCommodities();

        List<CommodityDto> commodityDtos = new ArrayList<CommodityDto>();
        for (Commodity commodity : comodities) {
            commodityDtos.add(new CommodityDto(commodity));
        }

        return new ResponseEntity<List<CommodityDto>>(commodityDtos, HttpStatus.OK);
    }

//    public ResponseEntity newCommodityGroup(){
//
//    }

    @GetMapping(value = "getGroups")
    public List<CommodityGroupe> getAllGroups(){

        return commodityService.getAllGroups() ;
    }

    @PostMapping(value = "addGroup/{name}")
    public CommodityGroupe addGroup(@PathVariable String name){
            try {
              CommodityGroupe commodityGroupe=  commodityService.addNewCommodityGroup(name);
              commodityGroupe.setTaxes(new HashSet<>());
                return commodityGroupe;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

    }

    @PostMapping(value = "addTax")
    public Tax addNewTax(@RequestParam String validFrom,@RequestParam int groupId,@RequestParam double percentage){
        try {
            return  commodityService.AddNewTaxToGroup(groupId,percentage,validFrom);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "getUnits")
    public List<Unit> getUnits(){

        return commodityService.getUnits() ;
    }

    @PostMapping(value = "new")
    public Commodity makeNew(@RequestBody NewCommodityDto newCommodityDto){
        try {
            return commodityService.addNewCommodity(newCommodityDto);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
