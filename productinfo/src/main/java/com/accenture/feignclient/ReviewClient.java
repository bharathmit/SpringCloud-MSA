package com.accenture.feignclient;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.model.Review;

@FeignClient(name = "review")
public interface ReviewClient {
	
	@RequestMapping(value="/reviews/{id}",method=RequestMethod.GET)
	public List<Review> getReviewByProductId(@PathVariable("id") final Long id);

}
