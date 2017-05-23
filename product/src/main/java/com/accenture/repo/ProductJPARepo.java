package com.accenture.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.entity.Product;

@Repository
public interface ProductJPARepo extends JpaRepository<Product, Long> {
	
	
	

}
