package com.jsoftgroup.feignclient.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private long prodId;
    private String description;

}
