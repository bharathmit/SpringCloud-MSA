package com.jsoftgroup.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jsoftgroup.model.Product;

@FeignClient(name = "product")
public interface ProductClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
	public Product getProductById(@PathVariable("id") final Long id);
}
