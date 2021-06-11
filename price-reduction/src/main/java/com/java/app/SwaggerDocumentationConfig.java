package com.java.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-11T06:55:46.430Z[GMT]")
@Configuration
public class SwaggerDocumentationConfig {
	
	@Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("io.swagger.api"))
                    .build()
                .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        	.title("Price Reduction REST Api")
        	.description("This is a Price Reduction REST Api server, creates a Restful webservice to request dresses that have a price reduction at [https://api.johnlewis.com/search/api/rest/v2/catalog/products/search/keyword?q=dresses](https://api.johnlewis.com/search/api/rest/v2/catalog/products/search/keyword?q=dresses&key=AIzaSyDD_6O5gUgC4tRW5f9kxC0_76XRC8W7_mI) ")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Price Reduction REST Api")
                .description("This is a Price Reduction REST Api server, creates a Restful webservice to request dresses that have a price reduction at [https://api.johnlewis.com/search/api/rest/v2/catalog/products/search/keyword?q=dresses](https://api.johnlewis.com/search/api/rest/v2/catalog/products/search/keyword?q=dresses&key=AIzaSyDD_6O5gUgC4tRW5f9kxC0_76XRC8W7_mI) ")
                .termsOfService("")
                .version("1.0.0")
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
	
}
