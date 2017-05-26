package com.accenture.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.model.ProductDetail;
import com.accenture.service.ProductService;


@RestController
@RequestMapping("/productinfo")
public class ProductInfoAPI {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public ProductDetail getProductById(@PathVariable("id") final Long id){
		ProductDetail productDetail=new ProductDetail();
		
		productDetail.setInventory(productService.getInventoryByProductId(id));
		productDetail.setProduct(productService.getProductById(id));
		productDetail.setReview(productService.getReviewByProductId(id));
		return productDetail;
	}
	
}
