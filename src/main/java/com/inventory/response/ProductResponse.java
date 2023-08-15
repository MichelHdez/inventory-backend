package com.inventory.response;

import java.util.List;

import com.inventory.model.Product;

import lombok.Data;

@Data
public class ProductResponse {

	List<Product> products;

	public ProductResponse() {
	}

	public ProductResponse(List<Product> products) {
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
