package com.accenture.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.model.Inventory;


@FeignClient(name = "inventory")
public interface InventoryClient {
	
	@RequestMapping(value ="/inventorys/{id}",method=RequestMethod.GET)
	public Inventory getInventoryByProductId(@PathVariable("id") final Long id);

}
