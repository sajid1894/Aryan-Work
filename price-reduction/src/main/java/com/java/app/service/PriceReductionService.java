package com.java.app.service;

import java.util.List;

import com.java.app.dto.ProductDto;

public interface PriceReductionService {
	public List<ProductDto> fetchAllProducts(String priceLabel);
}
