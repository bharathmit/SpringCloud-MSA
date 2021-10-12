package com.jsoftgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/*import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger.web.UiConfiguration;*/

@SpringBootApplication
@EnableDiscoveryClient

public class GatewayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	
	/*@Bean
	UiConfiguration uiConfig() {

		return new UiConfiguration("validatorUrl", "list", "alpha", "schema",
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
	}*/
}
