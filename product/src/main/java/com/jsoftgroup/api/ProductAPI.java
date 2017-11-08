package com.jsoftgroup.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;


import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import java.util.ArrayList;
import java.util.List;

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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@RestController
@Api(value = "Product API", description = "Endpoint for Product Management")
@RequestMapping("/products")
public class ProductAPI {
	
	private static final Log LOG = LogFactory.getLog(ProductAPI.class.getName());
	
	@Autowired
	ProductJPARepo productJPARepo;
	
	@HystrixCommand(fallbackMethod = "fallbackAddProduct")
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "Add Product details", notes = "New Product details can be added into server", response = Product.class,
			authorizations = { @Authorization(value = "OAuth2 authorization resource" , scopes = { @AuthorizationScope(scope = "server", description = "")} ) } )
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful added into Product details", response = Product.class)})
	public Product addProduct(@RequestBody Product product){
		LOG.info("Add Product details");
		return productJPARepo.saveAndFlush(product);
	}
	
	
	@HystrixCommand(fallbackMethod = "fallbackGetProduct")
	@ApiOperation( value = "List all Product", notes = "Returns a complete list of Product Details", response = Product.class, responseContainer = "List",
			authorizations = { @Authorization(value = "OAuth2 authorization resource" , scopes = { @AuthorizationScope(scope = "server", description = "")} ) })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of All Product details", response = Product.class)})
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> getProducts(){
		LOG.info("List all Product");
		return productJPARepo.findAll();
	}
	
	@ApiOperation(value = "Returns Product detail", notes = "Returns the prodct details by Product ID", response = Product.class,
			authorizations = { @Authorization(value = "OAuth2 authorization resource" , scopes = { @AuthorizationScope(scope = "server", description = "")} ) })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of Product detail", response = Product.class)})
	@ApiParam(name = "productId", value = "Unique ID for Product Item ", required = true)
	@RequestMapping(path="/{productId}",method=RequestMethod.GET)
	public Product getProductById(@PathVariable("productId") final Long productId){
		LOG.info("Returns Product detail");
		return productJPARepo.findOne(productId);
	}
	
	
	@ApiOperation(value = "Update Product Details", notes = "Update Product details can be added into server.", response = Product.class,
			authorizations = { @Authorization(value = "OAuth2 authorization resource" , scopes = { @AuthorizationScope(scope = "server", description = "")} ) })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful updated into Product detail", response = Product.class)})
	@RequestMapping(method=RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product){
		LOG.info("Update Product Details");
		return productJPARepo.saveAndFlush(product);
	}
	
	
	@HystrixCommand(fallbackMethod = "fallbackDeleteProduct")
	@ApiOperation(value = "Delete Product details", notes = "Delete Product details from server.", response = Product.class,
			authorizations = { @Authorization(value = "OAuth2 authorization resource" , scopes = { @AuthorizationScope(scope = "server", description = "")} ) })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful deleted the Product detail", response = String.class)})
	@ApiParam(name = "productId", value = "Unique ID for Product Item ", required = true)
	@RequestMapping(path="/{productId}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") final Long productId){
		LOG.info("Delete Product details");
		productJPARepo.delete(productId);
		return new ResponseEntity<String>("[\"Product deleted from table\"]", HttpStatus.OK);	
	}
	
	
	public Product fallbackAddProduct(Product product) {
		LOG.info("Fallback Method");
		return new Product();
	}
	
	
	public List<Product> fallbackGetProduct() {
		LOG.info("Fallback Method");
		return new ArrayList<Product>();
	}
	
	public ResponseEntity<String> fallbackDeleteProduct(Long productId) {
		LOG.info("Fallback Method");
		return new ResponseEntity<String>("[\"Hystrix fallback\"]", HttpStatus.OK);
	}
	

}
