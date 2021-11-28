package com.jsoftgroup.feignclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jsoftgroup.entity.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProductDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    Inventory inventory;
    List<Review> review;
    Product product;

}
