package sales.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sales.management.system.model.Commodity;

public interface CommodityRepository extends JpaRepository<Commodity, Integer>{



}
