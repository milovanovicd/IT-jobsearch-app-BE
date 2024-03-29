package com.jobsearch.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer{
	

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
				.apis(RequestHandlerSelectors.basePackage("com.jobsearch"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
    }
 
 
    private ApiInfo apiInfo() {
		return new ApiInfo("JobSearch API", 
				"Some description about my API", 
				"v1", 
				"Terms of Service Url", 
				new Contact("Dejan Milovanovic", "noUrl", "dejannn06@gmail.com"), 
				"Licence of API", 
				"Licences of URL", 
				Collections.emptyList());
	}


	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
