package sales.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sales.management.system.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

}
