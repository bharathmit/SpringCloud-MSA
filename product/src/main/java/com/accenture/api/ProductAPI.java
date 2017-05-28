package com.accenture.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.entity.Product;
import com.accenture.repo.ProductJPARepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/products")
public class ProductAPI {
	
	@Autowired
	ProductJPARepo productJPARepo;
	
	@HystrixCommand(fallbackMethod = "fallbackProduct")
	@RequestMapping(method=RequestMethod.POST)
	public Product addProduct(@RequestBody Product product){
		return productJPARepo.saveAndFlush(product);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> getProducts(){
		return productJPARepo.findAll();
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public Product getProductById(@PathVariable("id") final Long id){
		return productJPARepo.findOne(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product){
		return productJPARepo.saveAndFlush(product);
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@PathVariable("id") final Long id){
		productJPARepo.delete(id);
		return new ResponseEntity<String>("Invoice deleted from table", HttpStatus.OK);	
	}
	
	public Product fallbackProduct(Product product) {
		return new Product();
	}
	
	
	
	
	

}
