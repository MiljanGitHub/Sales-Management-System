package sales.management.system.service;

import net.sf.jasperreports.engine.JRException;
import sales.management.system.dtoResponse.JasperReportItemDto;
import sales.management.system.model.Invoice;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface JasperReportService {
    ArrayList<JasperReportItemDto>  getJasperReportInvoiceItems(int invoiceId);
    String exportReport(String reportFormat) throws FileNotFoundException, JRException;
}
