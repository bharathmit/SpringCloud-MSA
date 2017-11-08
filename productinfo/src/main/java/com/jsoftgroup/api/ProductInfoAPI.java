package com.jsoftgroup.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jsoftgroup.model.ProductDetail;
import com.jsoftgroup.service.ProductService;


@RestController
@RequestMapping("/productinfo")
public class ProductInfoAPI {
	
	private static final Log LOG = LogFactory.getLog(ProductInfoAPI.class.getName());
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public ProductDetail getProductById(@PathVariable("id") final Long id){
		LOG.info("get Product details By ID");
		ProductDetail productDetail=new ProductDetail();
		
		productDetail.setInventory(productService.getInventoryByProductId(id));
		productDetail.setProduct(productService.getProductById(id));
		productDetail.setReview(productService.getReviewByProductId(id));
		return productDetail;
	}
	
}
