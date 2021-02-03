package sales.management.system.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Miljan Puletic
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerPlugin() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .paths(PathSelectors.any())
	        .apis(RequestHandlerSelectors.any())
	        .build()
	        .securitySchemes(Arrays.asList(apiKey())) 
	        .securityContexts(Arrays.asList(securityContext()))
	    	.apiInfo(metaData());
    }
    
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API - Fleaty App")
                .description("*<b>To authenticate yourself do the following:</b>*\n"
                		+ "1. Use the POST /fleaty/login endpoint. Include your email and password in the body. Push token needs to be null.\n"
                		+ "2. Save the token value you get in response.\n"
                		+ "3. For API calls, use the token value in a HTTP request header as follows: \"Bearer [token value]\". For Swagger you use the Authorize button and insert \"Bearer [token value]\" in the text input.")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    @Bean
    public SecurityConfiguration security() {
	    return SecurityConfigurationBuilder.builder().scopeSeparator(",")
	        .additionalQueryStringParams(null)
	        .useBasicAuthenticationWithAccessCodeGrant(false).build();
    }

    private ApiKey apiKey() {
    	return new ApiKey("apiKey", "Authorization", "header");
    }

    private SecurityContext securityContext() {
	    return SecurityContext.builder().securityReferences(defaultAuth())
	        .forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope = new AuthorizationScope(
	        "global", "accessEverything");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    return Arrays.asList(new SecurityReference("apiKey",
	        authorizationScopes));
	    }
    
    @Bean
	UiConfiguration uiConfig() {
    	return UiConfigurationBuilder.builder()
    		.deepLinking(true)
		    .displayOperationId(false)
		    .defaultModelsExpandDepth(1)
		    .defaultModelExpandDepth(1)
		    .defaultModelRendering(ModelRendering.EXAMPLE)
		    .displayRequestDuration(true)
		    .docExpansion(DocExpansion.NONE)
		    .filter(false)
		    .maxDisplayedTags(null)
		    .operationsSorter(OperationsSorter.ALPHA)
		    .showExtensions(false)
		    .tagsSorter(TagsSorter.ALPHA)
		    .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
		    .validatorUrl(null)
		    .build();
	}
	
}
