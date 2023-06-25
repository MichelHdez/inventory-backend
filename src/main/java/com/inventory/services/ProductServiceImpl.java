package com.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inventory.dao.ICategoryDao;
import com.inventory.dao.IProductDao;
import com.inventory.model.Category;
import com.inventory.model.Product;
import com.inventory.response.ProductResponseRest;

@Service
public class ProductServiceImpl implements IProductService {

	private ICategoryDao categoryDao;
	private IProductDao productDao;

	public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
		this.categoryDao = categoryDao;
		this.productDao = productDao;
	}

	@Override
	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		
		try {
			
			Optional<Category> category = categoryDao.findById(categoryId);
			
			if (category.isPresent()) {
				product.setCategory(category.get());
			} else {
				response.setMetadata("respuesta nok", "-1", "Categoría no encontrada");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			//	save the product
			Product productSaved = productDao.save(product);
			if (productSaved != null) {
				list.add(productSaved);
				response.getProduct().setProducts(list);
				response.setMetadata("respuesta ok", "00", "Producto guardado");
			} else {
				response.setMetadata("respuesta nok", "-1", "Producto no guardado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al guardar");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

}
