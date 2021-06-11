package com.java.app.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1327791516764222913L;
	
	private String productId;
	private String title;
	private Price price;
	private List<ColorSwatch> colorSwatches;

}
