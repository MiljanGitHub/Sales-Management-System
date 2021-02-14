package sales.management.system.configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.RegionConflictException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Configuration
public class MinioConfiguration {
	
    @Value("${storage.minio.endpoint}")
    private String minioEndpoint;

    @Value("${storage.minio.invoice-bucket}")
    private String minioInvoiceBucketName;
    
    @Autowired
    private Environment environment;

    @Bean
    public MinioClient minIoClient() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException, IllegalArgumentException, InvalidBucketNameException, RegionConflictException {
        String accessKey = environment.getRequiredProperty("storage.minio.access-key");
        String secretKey = environment.getRequiredProperty("storage.minio.secret-key");

        MinioClient client =  MinioClient.builder()
                .endpoint(minioEndpoint)
                .credentials(accessKey, secretKey)
                .build();

        if(!client.bucketExists(BucketExistsArgs.builder().bucket(minioInvoiceBucketName).build())){
            client.makeBucket(MakeBucketArgs.builder().bucket(minioInvoiceBucketName).build());
        }
      
        return client;
    }

}