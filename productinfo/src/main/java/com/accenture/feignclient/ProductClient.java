package com.accenture.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.model.Product;

@FeignClient(name = "product")
public interface ProductClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
	public Product getProductById(@PathVariable("id") final Long id);
}
