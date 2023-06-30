package com.inventory.services;

import org.springframework.http.ResponseEntity;

import com.inventory.model.Product;
import com.inventory.response.ProductResponseRest;

public interface IProductService {

	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);
	public ResponseEntity<ProductResponseRest> searchById(Long id);
	public ResponseEntity<ProductResponseRest> searchByName(String name);
}
