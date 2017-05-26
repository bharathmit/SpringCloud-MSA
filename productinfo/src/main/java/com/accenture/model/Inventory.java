package com.accenture.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private long id;

	@Getter
	@Setter
	private long prodId;

	@Getter
	@Setter
	private int qty;

	@Getter
	@Setter
	private double price;
}
