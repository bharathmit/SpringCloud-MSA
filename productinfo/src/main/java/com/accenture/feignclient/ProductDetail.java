package com.accenture.feignclient;

import java.io.Serializable;
import java.util.List;

import com.accenture.model.Inventory;
import com.accenture.model.Product;
import com.accenture.model.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	Inventory inventory;
	
	@Getter
	@Setter
	List<Review> review;
	
	@Getter
	@Setter
	Product product;
	
	
}
