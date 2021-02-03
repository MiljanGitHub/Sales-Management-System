package sales.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sales.management.system.model.Commodity;

public interface CommodityRepository extends JpaRepository<Commodity, Integer>{

}
