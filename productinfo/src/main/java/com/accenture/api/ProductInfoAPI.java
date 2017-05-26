package com.accenture.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.feignclient.InventoryClient;
import com.accenture.feignclient.ProductClient;
import com.accenture.feignclient.ProductDetail;
import com.accenture.feignclient.ReviewClient;


@RestController
@RequestMapping("/productinfo")
public class ProductInfoAPI {

	@Autowired
	ProductClient productClient;
	
	@Autowired
	ReviewClient reviewClient;
	
	@Autowired
	InventoryClient inventoryClient;
	
	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public ProductDetail getProductById(@PathVariable("id") final Long id){
		ProductDetail productDetail=new ProductDetail();
		
		productDetail.setInventory(inventoryClient.getInventoryByProductId(id));
		productDetail.setProduct(productClient.getProductById(id));
		productDetail.setReview(reviewClient.getReviewByProductId(id));
		return productDetail;
	}
	
}
