package com.java.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 2511220439235205564L;
	
	@JsonProperty("products")
	private List<Product> products;
	
	public ProductResponse() {
		products = new ArrayList<>();
    }

}
