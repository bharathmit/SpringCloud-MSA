package com.jsoftgroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableConfigurationProperties
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients
public class ProductApplication {

	private static Logger logger  = LoggerFactory.getLogger(ProductApplication .class);

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@PostConstruct
	public void postConstruct(){

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
