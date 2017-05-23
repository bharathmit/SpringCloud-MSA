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

import com.accenture.entity.Inventory;
import com.accenture.repo.InventoryJPARepo;

@RestController
@RequestMapping("/inventorys")
public class InventoryAPI {
	
	@Autowired
	InventoryJPARepo inventoryJPARepo;
	
	@RequestMapping(method=RequestMethod.POST)
	public Inventory addInventory(@RequestBody Inventory inventory){
		return inventoryJPARepo.saveAndFlush(inventory);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Inventory> getInventorys(){
		return inventoryJPARepo.findAll();
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public Inventory getInventoryByProductId(@PathVariable("id") final Long id){
		return inventoryJPARepo.findByProdId(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Inventory updateInventory(@RequestBody Inventory inventory){
		return inventoryJPARepo.saveAndFlush(inventory);
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteInventory(@PathVariable("id") final Long id){
		inventoryJPARepo.deleteByProductId(id);
		return new ResponseEntity<String>("Inventory deleted from table", HttpStatus.OK);	
	}
	
	
	
	
	
	

}
