package com.java.app.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8968778982870195360L;
	
	private String productId;
	private String title;
	private List<ColorSwatchDto> colorSwatches;
	private String nowPrice;
	private String priceLabel;
	@JsonIgnore
	private Double priceReduced;
	
}
