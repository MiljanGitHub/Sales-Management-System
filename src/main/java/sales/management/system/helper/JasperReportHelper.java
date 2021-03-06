package sales.management.system.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sales.management.system.dtoResponse.InvoiceDto;
import sales.management.system.dtoResponse.JasperReportItemDto;
import sales.management.system.model.Invoice;
import sales.management.system.service.InvoiceService;


@Component
public class JasperReportHelper {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
    private MinioClient minioClient;
	
    @Value("${storage.minio.invoice-bucket}")
    private String minioInvoiceBucketName;
	
    @Value("${project.directory}")
    private String projectDirectory;
    
    @Value("${file.separtor}")
    private String fileSepartor;
    
	public void generateJasperReport(Invoice invoice)  {
		
		Runnable runnable = () -> { 
			
			ArrayList<JasperReportItemDto> jasperReportItemDtos = invoice.getItems()
					.stream().map(item -> new JasperReportItemDto(item))
					.collect(Collectors.toCollection(ArrayList::new));
			for (int i=0; i< jasperReportItemDtos.size();i++) {
				jasperReportItemDtos.get(i).setNumber(i+1);};
			String fileName = UUID.randomUUID().toString();
	        String extension = ".pdf";

			Path projectPath = FileSystems.getDefault().getPath(System.getProperty(projectDirectory) + System.getProperty(fileSepartor));
			
	        try {
	        	
	        	Path tempFile=Files.createTempFile(projectPath, fileName, extension);
	        	
				File file = ResourceUtils.getFile("classpath:invoice1.jrxml");
		        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jasperReportItemDtos);

		        Map<String, Object> parameters = new HashMap<>();
		        parameters.put("createdBy", "Sales Management App");
		        //BP - Business partner
				parameters.put("BPname",invoice.getPartner().getName());
				parameters.put("BPadress",invoice.getPartner().getAddress());
				parameters.put("BPemail",invoice.getPartner().getEmail());
				parameters.put("BPnumber",invoice.getPartner().getPhoneNumber());

				parameters.put("basisTotal",invoice.getBasisTotal());
				parameters.put("taxTotal",invoice.getTaxTotal());
				parameters.put("total",invoice.getTotalAmmount());

				long longValue = Long.parseLong(invoice.getInvoiceDate());
				LocalDate dateIssued =Instant.ofEpochMilli(longValue).atZone(ZoneId.systemDefault()).toLocalDate();
				parameters.put("dateIssued",dateIssued.toString());

				long longValue1 = Long.parseLong(invoice.getCurrencyDate());
				LocalDate currencyDate =Instant.ofEpochMilli(longValue1).atZone(ZoneId.systemDefault()).toLocalDate();
				parameters.put("currencyDate",currencyDate.toString());

				parameters.put("InvoiceNumber",invoice.getInvoiceNumber());



				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		        JasperExportManager.exportReportToPdfFile(jasperPrint, tempFile.toAbsolutePath().toString());
		        
		        InputStream targetStream = new FileInputStream(tempFile.toFile());

				minioClient.putObject(
						PutObjectArgs.builder()
						.bucket(minioInvoiceBucketName)
						.object(fileName).stream(
	 		    	    		targetStream, -1, 10485760)
	 		    	        .contentType("application/pdf") //for now only PDF documents
	 		    	        .build());
				
				//close stream so that temp file can be deleted
				targetStream.close();
				
				//delete temp file from project directory
				Files.delete(tempFile);
				
				String url =
	             	    minioClient.getPresignedObjectUrl(
	             	        GetPresignedObjectUrlArgs.builder()
	             	            .method(Method.GET)
	             	            .bucket(minioInvoiceBucketName)
	             	            .object(fileName)
	             	            .expiry(7, TimeUnit.DAYS)
	             	            .build());
				
				invoice.setUrl(url);
				
				invoiceService.save(invoice);
				
		        
			} catch (InvalidKeyException | ErrorResponseException     | IllegalArgumentException | InsufficientDataException
					| InternalException  | InvalidBucketNameException | InvalidResponseException | NoSuchAlgorithmException
					| ServerException    | XmlParserException         | IOException  e ) {
				
				e.printStackTrace();

			
			} catch (Exception e) {
	         	e.printStackTrace();
	

	 		}

		};
		Thread t = new Thread(runnable);
		t.start();
	}

	public String generateInvoiceBook(List<InvoiceDto> invoices,String from, String to){

		for (int i=0; i< invoices.size();i++) {
			invoices.get(i).setId(i+1);};

			String fileName = "invoiceBook";
			String extension = ".pdf";

			Path projectPath = FileSystems.getDefault().getPath(System.getProperty(projectDirectory) + System.getProperty(fileSepartor));

			try {

				Path tempFile=Files.createTempFile(projectPath, fileName, extension);

				File file = ResourceUtils.getFile("classpath:invoiceBook.jrxml");
				JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoices);

				Map<String, Object> parameters = new HashMap<>();
				long longValue = Long.parseLong(from);
				LocalDate dateIssued =Instant.ofEpochMilli(longValue).atZone(ZoneId.systemDefault()).toLocalDate();
				parameters.put("dateFrom",dateIssued.toString());

				long longValue1 = Long.parseLong(to);
				LocalDate currencyDate =Instant.ofEpochMilli(longValue1).atZone(ZoneId.systemDefault()).toLocalDate();
				parameters.put("dateTo",currencyDate.toString());
//
//				parameters.put("dateFrom", from);
//				parameters.put("dateTo", to);




				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

				JasperExportManager.exportReportToPdfFile(jasperPrint, tempFile.toAbsolutePath().toString());

				InputStream targetStream = new FileInputStream(tempFile.toFile());

				minioClient.putObject(
						PutObjectArgs.builder()
								.bucket(minioInvoiceBucketName)
								.object(fileName).stream(
								targetStream, -1, 10485760)
								.contentType("application/pdf") //for now only PDF documents
								.build());

				//close stream so that temp file can be deleted
				targetStream.close();

				//delete temp file from project directory
				Files.delete(tempFile);

				String url =
						minioClient.getPresignedObjectUrl(
								GetPresignedObjectUrlArgs.builder()
										.method(Method.GET)
										.bucket(minioInvoiceBucketName)
										.object(fileName)
										.expiry(7, TimeUnit.DAYS)
										.build());

				return url;


			} catch (Exception e) {
				e.printStackTrace();

				return "Error generating invoice book";

			}



	}

}
