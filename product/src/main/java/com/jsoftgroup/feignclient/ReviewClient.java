package com.jsoftgroup.feignclient;

import com.jsoftgroup.feignclient.model.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "review")
public interface ReviewClient {

    @RequestMapping(value="/reviews/products/{id}",method= RequestMethod.GET)
    public List<Review> getReviewByProductId(@PathVariable("id") final Long id);

}
