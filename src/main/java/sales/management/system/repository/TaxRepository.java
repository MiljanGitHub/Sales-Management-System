package sales.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sales.management.system.dtoResponse.RawTax;
import sales.management.system.model.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer>{
	
	@Query(value = "SELECT t.percentage From Tax t WHERE t.commodity_groupe_id = :commodityGroupId AND (t.valid_from+0) <= :requestedTime", nativeQuery = true)
	int findTaxRateForCommodityGroup(@Param("requestedTime") long requestedTime, @Param("commodityGroupId") int commodityGroupId);
	
	@Query(name = "findRawTaxValuesPerCommodityGroup", nativeQuery = true)
	RawTax findRawTaxValuesPerCommodityGroup(@Param("commodityGroupId") int commodityGroupId, @Param("requestedTime") long requestedTime);

	@Query(value = "select *  from tax where commodity_groupe_id = :commodityGroupId and valid_from in (SELECT MAX(t.valid_from) FROM tax t WHERE t.valid_from + 0 <= :requestedTime);", nativeQuery = true)
	Tax findTax(@Param("commodityGroupId") int commodityGroupId, @Param("requestedTime") long requestedTime);
}
