package com.jsoftgroup.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jsoftgroup.entity.Product;
import com.jsoftgroup.feignclient.model.ProductDetail;
import com.jsoftgroup.repo.ProductJPARepo;
import com.jsoftgroup.service.ProductDetailService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



@RestController
@RequestMapping("/products")
public class ProductAPI {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductJPARepo productJPARepo;

	@Autowired
	ProductDetailService productDetailService;
	
	@CircuitBreaker(name = "productAPI", fallbackMethod = "fallbackAddProduct")
	@RequestMapping(method=RequestMethod.POST)
	public Product addProduct(@RequestBody Product product){
		LOGGER.info("Add Product details");
		return productJPARepo.saveAndFlush(product);
	}

	@CircuitBreaker(name = "productAPI", fallbackMethod = "fallbackGetProduct")
	public List<Product> getProducts(){
		LOGGER.trace("This is a trace message. ");
		LOGGER.debug("This is a debug message.");
		LOGGER.info("This is an info message.");
		LOGGER.warn("This is a warn message.");
		LOGGER.error("This is an error message.");
		return productJPARepo.findAll();
	}

	@CircuitBreaker(name = "productAPI", fallbackMethod = "fallbackDeleteProduct")
	@RequestMapping(path="/{productId}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") final Long productId){
		LOGGER.info("Delete Product details");
		productJPARepo.deleteById(productId);
		return new ResponseEntity<String>("[\"Product deleted from table\"]", HttpStatus.OK);
	}
	
	@RequestMapping(path="/{productId}",method=RequestMethod.GET)
	public Product getProductById(@PathVariable("productId") final Long productId){
		LOGGER.info("Returns Product detail");
		return productJPARepo.findById(productId).orElse(null);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product){
		LOGGER.info("Update Product Details");
		return productJPARepo.saveAndFlush(product);
	}

	@RequestMapping(path="/details/{id}",method=RequestMethod.GET)
	public ProductDetail getProductDetailsById(@PathVariable("id") final Long id){
		LOGGER.info("get Product details By ID");
		ProductDetail productDetail=new ProductDetail();

		productDetail.setProduct(getProductById(id));
		productDetail.setInventory(productDetailService.getInventoryByProductId(id));
		productDetail.setReview(productDetailService.getReviewByProductId(id));
		return productDetail;
	}
	
	
	public Product fallbackAddProduct(Product product,Exception exception) {
		LOGGER.info("Fallback Method Call");
		return new Product();
	}
	
	
	public List<Product> fallbackGetProduct(Exception exception) {
		LOGGER.info("Fallback Method Call");
		return new ArrayList<Product>();
	}
	
	public ResponseEntity<String> fallbackDeleteProduct(Long productId,Exception exception) {
		LOGGER.info("Fallback Method Call");
		return new ResponseEntity<String>("[\"fallback method\"]", HttpStatus.OK);
	}
	

}
