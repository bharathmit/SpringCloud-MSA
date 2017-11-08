package com.jsoftgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrix
@EnableFeignClients
public class ProductInfoApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ProductInfoApplication.class, args);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**");
	}
	
	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
	

}
