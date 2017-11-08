package com.jsoftgroup.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsoftgroup.entity.Product;

@Repository
public interface ProductJPARepo extends JpaRepository<Product, Long> {
	
	
	

}
