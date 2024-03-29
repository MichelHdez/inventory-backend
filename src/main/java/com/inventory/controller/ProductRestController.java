package com.inventory.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inventory.model.Product;
import com.inventory.response.ProductResponseRest;
import com.inventory.services.IProductService;
import com.inventory.util.Util;

/**
 * @author Mike
 *
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class ProductRestController {
	
	private IProductService productService;
	
	public ProductRestController(IProductService productService) {
		this.productService = productService;
	}
	/**
	 * 
	 * @param picture
	 * @param name
	 * @param price
	 * @param amount
	 * @param categoryId
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> save (
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("amount") int amount,
			@RequestParam("categoryId") Long categoryId) throws IOException
	{
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setAmount(amount);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		ResponseEntity<ProductResponseRest> response = productService.save(product, categoryId);
		return response; 
	}
	
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> searchById(@PathVariable Long id) {
		ResponseEntity<ProductResponseRest> response = productService.searchById(id);
		return response;
	}
	
	/**
	 * search by name
	 * @param name
	 * @return
	 */
	@GetMapping("/products/filter/{name}")
	public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name) {
		ResponseEntity<ProductResponseRest> response = productService.searchByName(name);
		return response;
	}
	
	/**
	 * delete by id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> deleteById(@PathVariable Long id){
		ResponseEntity<ProductResponseRest> response =  productService.deleteById(id);
		return response;
	}

	
	/** search by name
	 * @return
	 */
	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> search() {
		ResponseEntity<ProductResponseRest> response = productService.search();
		return response;
	}

	/**
	 * update product
	 * @param picture
	 * @param name
	 * @param price
	 * @param amount
	 * @param categoryId
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> update (
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("amount") int amount,
			@RequestParam("categoryId") Long categoryId,
			@PathVariable Long id) throws IOException
	{
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setAmount(amount);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		ResponseEntity<ProductResponseRest> response = productService.update(product, categoryId, id);
		return response; 
	}
	
}
