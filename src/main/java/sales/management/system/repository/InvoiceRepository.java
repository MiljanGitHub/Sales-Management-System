package sales.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sales.management.system.dtoResponse.InvoiceDto;
import sales.management.system.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	
	
	@Query(name = "findInvoicesByDate", nativeQuery = true)
	List<InvoiceDto> findInvoicesByDate(@Param("from") long from, @Param("to") long to);

}
