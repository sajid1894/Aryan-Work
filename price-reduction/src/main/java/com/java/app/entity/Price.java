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
public class Price implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3881190132268661100L;
	private String was;
	private String then1;
	private String then2;
	private Object now;
	private String currency;
}
