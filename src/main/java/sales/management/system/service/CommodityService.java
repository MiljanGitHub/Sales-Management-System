package sales.management.system.service;
import java.util.List;

import sales.management.system.dtoRequest.NewCommodityDto;
import sales.management.system.model.Commodity;
import sales.management.system.model.CommodityGroupe;
import sales.management.system.model.Tax;
import sales.management.system.model.Unit;

public interface CommodityService {


    List<Commodity>  getAllCommodities();
    Commodity findById(int commodityId);
    List<CommodityGroupe> getAllGroups();

    CommodityGroupe addNewCommodityGroup(String name);

    Tax AddNewTaxToGroup(int groupId, double percentage, String validFrom);

    Commodity addNewCommodity(NewCommodityDto newCommodityDto);

    List<Unit> getUnits();
}
