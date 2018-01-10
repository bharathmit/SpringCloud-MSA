package com.jsoftgroup.api;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import com.jsoftgroup.entity.Inventory;
import com.jsoftgroup.repo.InventoryJPARepo;

@RestController
@RequestMapping("/inventorys")
public class InventoryAPI {
	
	private static final Log LOG = LogFactory.getLog(InventoryAPI.class.getName());
	
	@Autowired
	InventoryJPARepo inventoryJPARepo;
	
	@RequestMapping(method=RequestMethod.POST)
	public Inventory addInventory(@RequestBody Inventory inventory){
		LOG.info("addInventory");
		return inventoryJPARepo.saveAndFlush(inventory);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Inventory> getInventorys(){
		LOG.info("getInventorys");
		return inventoryJPARepo.findAll();
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public Inventory getInventoryByProductId(@PathVariable("id") final Long id){
		LOG.info("getInventoryByProductId");
		return inventoryJPARepo.findByProdId(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Inventory updateInventory(@RequestBody Inventory inventory){
		LOG.info("updateInventory");
		return inventoryJPARepo.saveAndFlush(inventory);
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteInventory(@PathVariable("id") final Long id){
		LOG.info("deleteInventory");
		inventoryJPARepo.deleteByProductId(id);
		return new ResponseEntity<String>("Inventory deleted from table", HttpStatus.OK);	
	}
	
	public String getService(String name){
		String value="User Not Found";
		
		if(StringUtils.isEmpty(name)){
			value="Power Mock Test";
			inventoryJPARepo.deleteByProductId(1l);
		}
		if("bharath".equalsIgnoreCase(name)){
			value="Hello Bharath";
		}
		if("sriram".equalsIgnoreCase(name)){
			value="Hello Sriram";
		}
		if(name.equalsIgnoreCase("pavithira")){
			value="Hello Pavithira";
		}
		return value;
	}
	
	
	
	
	

}
