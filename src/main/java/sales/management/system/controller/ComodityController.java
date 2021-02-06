package sales.management.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sales.management.system.dto.CommodityDto;
import sales.management.system.model.Commodity;
import sales.management.system.service.CommodityService;

import java.util.ArrayList;
import java.util.List;

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


}
