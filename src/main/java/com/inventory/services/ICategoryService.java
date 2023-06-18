package com.inventory.services;

import org.springframework.http.ResponseEntity;

import com.inventory.model.Category;
import com.inventory.response.CategoryResponseRest;

public interface ICategoryService {

	public ResponseEntity<CategoryResponseRest> search();
	
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	
	public ResponseEntity<CategoryResponseRest> save(Category category);
	
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	
	public ResponseEntity<CategoryResponseRest> deleteById(Long id);
}
