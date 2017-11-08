package com.jsoftgroup.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private long prodId;
	
	@Getter
	@Setter
	private String prodDescription;

}
