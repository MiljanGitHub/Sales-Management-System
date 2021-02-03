package sales.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sales.management.system.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

}
