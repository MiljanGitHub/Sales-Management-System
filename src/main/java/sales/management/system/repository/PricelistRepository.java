package sales.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sales.management.system.dtoResponse.PricelistDetailDto;
import sales.management.system.dtoResponse.PricelistDto;
import sales.management.system.model.Company;
import sales.management.system.model.Pricelist;

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Integer> {
	
	/*
	 * Using JQPL to fetch DTO
	 */
	@Query(value = "SELECT new sales.management.system.dtoResponse.PricelistDto(pricelist.id, pricelist.validFrom) FROM Pricelist pricelist WHERE pricelist.company = :company")
	List<PricelistDto> findPricelistDto(@Param("company") Company company);
	
	@Query(name = "findPricelistItemDetails", nativeQuery = true)
	List<PricelistDetailDto> findPricelistItemDetails(@Param("pricelistId") int pricelistId);

}
