package sales.management.system.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sales.management.system.model.Commodity;

public interface CommodityRepository extends JpaRepository<Commodity, Integer>{

    @Query(value = "select * from commodity where commodity_id  not in (select p.commodity_id from pricelist_item as p INNER JOIN pricelist_item_pricelist   ON  p.pricelistitem_id=pricelist_item_pricelist.pricelist_item_id where pricelist_id = :priceListId)", nativeQuery = true)
    ArrayList<Commodity> getCommoditiesNotInPriceList(@Param("priceListId") int priceListId);

}
