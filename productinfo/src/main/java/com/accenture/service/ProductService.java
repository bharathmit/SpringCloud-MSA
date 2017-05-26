package com.accenture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.feignclient.InventoryClient;
import com.accenture.feignclient.ProductClient;
import com.accenture.feignclient.ReviewClient;
import com.accenture.model.Inventory;
import com.accenture.model.Product;
import com.accenture.model.Review;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ProductService {

	@Autowired
	ProductClient productClient;

	@Autowired
	ReviewClient reviewClient;

	@Autowired
	InventoryClient inventoryClient;

	@HystrixCommand(fallbackMethod = "fallbackReview")
	public List<Review> getReviewByProductId(Long id) {
		return reviewClient.getReviewByProductId(id);
	}

	@HystrixCommand(fallbackMethod = "fallbackProduct")
	public Product getProductById(Long id) {
		return productClient.getProductById(id);
	}

	@HystrixCommand(fallbackMethod = "fallbackInventory")
	public Inventory getInventoryByProductId(Long id) {
		return inventoryClient.getInventoryByProductId(id);
	}

	public List<Review> fallbackReview(Long id) {
		return new ArrayList<Review>();
	}

	public Product fallbackProduct(Long id) {
		return new Product();
	}
	
	public Inventory fallbackInventory(Long id){
		return new Inventory();
	}

}
