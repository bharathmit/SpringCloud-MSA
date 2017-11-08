package com.jsoftgroup.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsoftgroup.feignclient.InventoryClient;
import com.jsoftgroup.feignclient.ProductClient;
import com.jsoftgroup.feignclient.ReviewClient;
import com.jsoftgroup.model.Inventory;
import com.jsoftgroup.model.Product;
import com.jsoftgroup.model.Review;
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
