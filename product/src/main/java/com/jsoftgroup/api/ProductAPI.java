package com.jsoftgroup.api;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

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
import com.jsoftgroup.repo.ProductJPARepo;



@RestController
@RequestMapping("/products")
public class ProductAPI {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductJPARepo productJPARepo;
	
	@CircuitBreaker(name = "productAPI", fallbackMethod = "fallbackAddProduct")
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "Add Product details", notes = "New Product details can be added into server", response = Product.class,
			authorizations = { @Authorization(value = "OAuth2 authorization resource" , scopes = { @AuthorizationScope(scope = "server", description = "")} ) } )
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful added into Product details", response = Product.class)})
	public Product addProduct(@RequestBody Product product){
		LOGGER.info("Add Product details");
		return productJPARepo.saveAndFlush(product);
	}

	@CircuitBreaker(name = "productAPI", fallbackMethod = "fallbackGetProduct")
	@ApiOperation( value = "List all Product", notes = "Returns a complete list of Product Details", response = Product.class, responseContainer = "List",
			authorizations = { @Authorization(value = "OAuth2 authorization resource" , scopes = { @AuthorizationScope(scope = "server", description = "")} ) })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of All Product details", response = Product.class)})
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> getProducts(){
		LOGGER.trace("This is a trace message. ");
		LOGGER.debug("This is a debug message.");
		LOGGER.info("This is an info message.");
		LOGGER.warn("This is a warn message.");
		LOGGER.error("This is an error message.");
		return productJPARepo.findAll();
	}

	@CircuitBreaker(name = "productAPI", fallbackMethod = "fallbackDeleteProduct")
	@ApiOperation(value = "Delete Product details", notes = "Delete Product details from server.", response = Product.class,
			authorizations = { @Authorization(value = "OAuth2 authorization resource" , scopes = { @AuthorizationScope(scope = "server", description = "")} ) })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful deleted the Product detail", response = String.class)})
	@ApiParam(name = "productId", value = "Unique ID for Product Item ", required = true)
	@RequestMapping(path="/{productId}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") final Long productId){
		LOGGER.info("Delete Product details");
		productJPARepo.deleteById(productId);
		return new ResponseEntity<String>("[\"Product deleted from table\"]", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Returns Product detail", notes = "Returns the prodct details by Product ID", response = Product.class,
			authorizations = { @Authorization(value = "OAuth2 authorization resource" , scopes = { @AuthorizationScope(scope = "server", description = "")} ) })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of Product detail", response = Product.class)})
	@ApiParam(name = "productId", value = "Unique ID for Product Item ", required = true)
	@RequestMapping(path="/{productId}",method=RequestMethod.GET)
	public Product getProductById(@PathVariable("productId") final Long productId){
		LOGGER.info("Returns Product detail");
		return productJPARepo.findById(productId).orElse(null);
	}
	
	
	@ApiOperation(value = "Update Product Details", notes = "Update Product details can be added into server.", response = Product.class,
			authorizations = { @Authorization(value = "OAuth2 authorization resource" , scopes = { @AuthorizationScope(scope = "server", description = "")} ) })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful updated into Product detail", response = Product.class)})
	@RequestMapping(method=RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product){
		LOGGER.info("Update Product Details");
		return productJPARepo.saveAndFlush(product);
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
