package com.accenture.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel( value = "Product", description = "Product resource representation" )
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	@ApiModelProperty( value = "Product Id", required = true ) 
	private long prodId;

	@Column(length = 200)
	@Getter
	@Setter
	@ApiModelProperty( value = "Product Description", required = true ) 
	private String prodDescription;

}
