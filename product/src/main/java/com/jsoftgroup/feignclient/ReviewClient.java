package com.jsoftgroup.feignclient;

import com.jsoftgroup.feignclient.model.Review;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "review")
public interface ReviewClient {

    final Logger logger = LoggerFactory.getLogger(ReviewClient.class);

    @CircuitBreaker(name = "reviewSearch",fallbackMethod = "cbFallBack")
    //@Retry(name = "reviewSearch",fallbackMethod = "cbFallBack")
    @RequestMapping(value="/reviews/products/{id}",method= RequestMethod.GET)
    public List<Review> getReviewByProductId(@PathVariable("id") final Long id);

    public default List<Review> cbFallBack(Exception exception) {
        logger.error(String.format("Fallback Execution for Circuit Breaker. Error Message: %s\n",exception.getMessage()));
        return null;
    }

}
