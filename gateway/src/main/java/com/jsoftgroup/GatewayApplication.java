package com.jsoftgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableSwagger2
public class GatewayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
	
	
	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl", "list", "alpha", "schema",
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
	}
}
