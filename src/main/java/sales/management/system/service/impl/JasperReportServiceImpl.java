package sales.management.system.service.impl;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import sales.management.system.dtoResponse.JasperReportItemDto;
import sales.management.system.model.Invoice;
import sales.management.system.model.InvoiceItem;
import sales.management.system.repository.InvoiceRepository;
import sales.management.system.service.JasperReportService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperReportServiceImpl implements JasperReportService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public ArrayList<JasperReportItemDto> getJasperReportInvoiceItems(int  invoiceId) {
        Invoice invoice =invoiceRepository.findById(invoiceId).get();

        ArrayList<JasperReportItemDto> jasperReportItemDtos = new ArrayList<>();
        for(InvoiceItem invoiceItem:invoice.getItems()){
            JasperReportItemDto j=new JasperReportItemDto(invoiceItem);
            jasperReportItemDtos.add(j);
        }

        return jasperReportItemDtos;
    }

    @Override
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Sasa\\Desktop\\Report";
        ArrayList<JasperReportItemDto> jasperReportItemDtos = getJasperReportInvoiceItems(1);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:invoice1.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jasperReportItemDtos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");


        return "report generated in path : " + path;
    }
}
