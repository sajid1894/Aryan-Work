package com.java.app.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorSwatch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2874644930429824055L;
	
	private String basicColor;
	private String skuId;

}
