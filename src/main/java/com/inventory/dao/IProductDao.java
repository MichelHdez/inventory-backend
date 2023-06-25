package com.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.inventory.model.Product;

public interface IProductDao extends CrudRepository<Product, Long>{

}
