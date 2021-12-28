package com.jsoftgroup.feignclient;

import com.jsoftgroup.feignclient.model.Inventory;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "inventory", url = "http://localhost:8020/" )
public interface InventoryClient {

    final Logger logger = LoggerFactory.getLogger(InventoryClient.class);

    @Retry(name = "inventorySearch",fallbackMethod = "retryFallBack")
    @RequestMapping(value ="/inventorys/products/{id}",method= RequestMethod.GET)
    public Inventory getInventoryByProductId(@PathVariable("id") final Long id);

    public default Inventory retryFallBack(Exception exception) {
        logger.error(String.format("Fallback Execution for Circuit Breaker. Error Message: %s\n",exception.getMessage()),exception);
        return null;
    }




}
