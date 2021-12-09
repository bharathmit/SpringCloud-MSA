package com.jsoftgroup;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableConfigurationProperties
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients
public class ProductApplication {
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	
	/*@Bean
	public Docket api() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        
        Set<String> consumes = new HashSet<String>();
        consumes.add("application/json");
        
        Set<String> produces = new HashSet<String>();
        produces.add("application/json");
        
		return new Docket(DocumentationType.SWAGGER_2)  
                .select() 
                .apis(RequestHandlerSelectors.basePackage("com.jsoftgroup.api"))
                .paths(PathSelectors.any())                          
                .build().apiInfo(new ApiInfo("Product Service Api Documentation", "Documentation automatically generated", model.getParent().getVersion(), null, 
                		new Contact("Bharath Mannaperumal", "http://jsoftgroup.wordpress.com", "bharathkumar.feb14@gmail.com"), "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0"))
                		.consumes(consumes).produces(produces);
	}*/
	
	
}
