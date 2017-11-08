package com.jsoftgroup.repo;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsoftgroup.entity.Inventory;

@Repository
public interface InventoryJPARepo extends JpaRepository<Inventory, Long> {
	
	Inventory findByProdId(Long prodId);
	
	@Transactional
	@Modifying 
	@Query("delete Inventory where prodId = :prodId")
	int deleteByProductId(@Param("prodId") Long prodId);
	

}
