package com.jsoftgroup;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
//import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;



import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/*import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.jsoftgroup.security.CustomUserInfoTokenServices;

import feign.RequestInterceptor;*/
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//@EnableResourceServer
//@EnableOAuth2Client
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
@EnableCircuitBreaker
@EnableHystrix 
public class ProductApplication extends WebMvcConfigurerAdapter /*extends ResourceServerConfigurerAdapter*/  {
	
	//@Autowired
	//private ResourceServerProperties sso;
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
	
	
	@Bean
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
	}
	
	/*@Bean
	@ConfigurationProperties(prefix = "security.oauth2.client")
	public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
		return new ClientCredentialsResourceDetails();
	}

	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor(){
		return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
	}

	@Bean
	public OAuth2RestTemplate clientCredentialsRestTemplate() {
		return new OAuth2RestTemplate(clientCredentialsResourceDetails());
	}

	@Bean
	public ResourceServerTokenServices tokenServices() {
		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/" , "/demo").permitAll()
				.anyRequest().authenticated();
	}*/
}
