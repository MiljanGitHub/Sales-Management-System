package sales.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sales.management.system.dtoResponse.RawPricelistItem;
import sales.management.system.model.PricelistItem;

@Repository
public interface PricelistItemRepository extends JpaRepository<PricelistItem, Integer>{
	
	 @Query(name = "findPricelistItems", nativeQuery = true)
	 List<RawPricelistItem> findPricelistItems(@Param("requestedTime")Long requestedTime);
	

}
