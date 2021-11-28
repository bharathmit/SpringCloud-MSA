package com.jsoftgroup.api;

import java.util.List;

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

import com.jsoftgroup.entity.Review;
import com.jsoftgroup.repo.ReviewJPARepo;

@RestController
@RequestMapping("/reviews")
public class ReviewAPI {
	
	private static final Log LOG = LogFactory.getLog(ReviewAPI.class.getName());
	
	@Autowired
	ReviewJPARepo reviewJPARepo;
	
	@RequestMapping(method=RequestMethod.POST)
	public Review addReview(@RequestBody Review review){
		LOG.info("addReview");
		return reviewJPARepo.saveAndFlush(review);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Review> getReviews(){
		LOG.info("getReviews");
		return reviewJPARepo.findAll();
	}

	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public Review getReviewById(@PathVariable("id") final Long id){
		LOG.info("getReviewById");
		return reviewJPARepo.findById(id).orElse(null);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Review updateReview(@RequestBody Review review){
		LOG.info("updateReview");
		return reviewJPARepo.saveAndFlush(review);
	}
	
	@RequestMapping(path="{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteReview(@PathVariable("id") final Long id){
		LOG.info("deleteReview");
		reviewJPARepo.deleteById(id);
		return new ResponseEntity<String>("Review deleted from table", HttpStatus.OK);
	}


	@RequestMapping(path="/products/{id}",method=RequestMethod.GET)
	public List<Review> getReviewByProductId(@PathVariable("id") final Long id){
		LOG.info("getReviewByProductId");
		return reviewJPARepo.findByProdId(id);
	}

	@RequestMapping(path="/products/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteByProductId(@PathVariable("id") final Long id){
		LOG.info("deleteByProductId");
		reviewJPARepo.deleteByProductId(id);
		return new ResponseEntity<String>("Review deleted from table", HttpStatus.OK);
	}
	
	
	

}
