package com.rest.spring.Api.Config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket getDocket()
	{
		return new Docket(DocumentationType.SWAGGER_2).groupName("Customer-Bank-Application").select()
													  .apis(RequestHandlerSelectors.any())
													  .paths(PathSelectors.any()).build()
													  .apiInfo(getApiInfo());
	
	}
	
	private ApiInfo getApiInfo()
	{
		ApiInfo apiInfo=new ApiInfo("Customer Bank Service API",
									"Customer Bank Service API Documentation",
									"1.0",
									"Terms of Service",
									new Contact("Mehmet Ali Demir","https://github.com/mhmtdmr53", "mehmetalidemir3496@gmail.com"),
									"Apache 2.0",
									"http://www.apache.org/licenses/LICENSE-2.0",new ArrayList<>());
		
		return apiInfo;
	}

}
