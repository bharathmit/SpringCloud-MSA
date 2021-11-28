package com.jsoftgroup.feignclient;

import com.jsoftgroup.feignclient.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "inventory")
public interface InventoryClient {

    @RequestMapping(value ="/inventorys/products/{id}",method= RequestMethod.GET)
    public Inventory getInventoryByProductId(@PathVariable("id") final Long id);





}
