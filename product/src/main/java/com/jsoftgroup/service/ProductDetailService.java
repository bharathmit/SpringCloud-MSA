package com.jsoftgroup.service;

import com.jsoftgroup.feignclient.InventoryClient;
import com.jsoftgroup.feignclient.ReviewClient;
import com.jsoftgroup.feignclient.model.Inventory;
import com.jsoftgroup.feignclient.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailService {


    @Autowired
    ReviewClient reviewClient;

    @Autowired
    InventoryClient inventoryClient;

    public List<Review> getReviewByProductId(Long id) {
        return reviewClient.getReviewByProductId(id);
    }

    public Inventory getInventoryByProductId(Long id) {
        return inventoryClient.getInventoryByProductId(id);
    }





}
