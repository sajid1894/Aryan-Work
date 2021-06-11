package com.java.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorSwatchDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5667703067990044098L;
	
	private String color;
	private String rgbColor;
	private String skuId;
	
}
