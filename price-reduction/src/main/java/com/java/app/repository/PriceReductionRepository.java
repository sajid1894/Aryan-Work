package com.java.app.repository;

import java.util.List;

import com.java.app.entity.Product;

public interface PriceReductionRepository {
	public List<Product> fetchAllProducts();
}
