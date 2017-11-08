package com.jsoftgroup.model;

import java.io.Serializable;
import java.util.List;

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
