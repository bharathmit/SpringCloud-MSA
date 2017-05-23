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

import com.accenture.entity.Review;
import com.accenture.repo.ReviewJPARepo;

@RestController
@RequestMapping("/reviews")
public class ReviewAPI {
	
	@Autowired
	ReviewJPARepo reviewJPARepo;
	
	@RequestMapping(method=RequestMethod.POST)
	public Review addReview(@RequestBody Review review){
		return reviewJPARepo.saveAndFlush(review);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Review> getReviews(){
		return reviewJPARepo.findAll();
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public List<Review> getReviewByProductId(@PathVariable("id") final Long id){
		return reviewJPARepo.findByProdId(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Review updateReview(@RequestBody Review review){
		return reviewJPARepo.saveAndFlush(review);
	}
	
	@RequestMapping(path="/review/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteReview(@PathVariable("id") final Long id){
		reviewJPARepo.delete(id);
		return new ResponseEntity<String>("Invoice deleted from table", HttpStatus.OK);	
	}
	
	
	@RequestMapping(path="/product/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteByProductId(@PathVariable("id") final Long id){
		reviewJPARepo.deleteByProductId(id);
		return new ResponseEntity<String>("Invoice deleted from table", HttpStatus.OK);	
	}
	
	
	

}
