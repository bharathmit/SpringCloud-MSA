package com.accenture.repo;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.entity.Review;

@Repository
public interface ReviewJPARepo extends JpaRepository<Review, Long> {
	
	List<Review> findByProdId(Long prodId);
	
	@Transactional
	@Modifying 
	@Query("delete Review where prodId = :prodId")
	int deleteByProductId(@Param("prodId") Long prodId);
	

}
