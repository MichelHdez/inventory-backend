package com.inventory.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.inventory.model.Category;
import com.inventory.response.CategoryResponseRest;
import com.inventory.services.ICategoryService;

class CategoryRestControllerTest {

	// Buscar todas las dependencias que tenga @Mock para usar esa dependencia	
	@InjectMocks
	CategoryRestController categoryRestController;
	
	// creamos una copia de este servicio para no enviar la respuesta a la BD	
	@Mock
	private ICategoryService service;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void saveTest() {
		// estos obejtos simulan una respuesta Http para no enviarlo a BD		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		// creamos un objeto categoria y seteamos su valores
		Category category =  new Category();
		category.setId(Long.valueOf(1));
		category.setName("Lacteos");
		category.setDescription("productos derivados de la leche");
		
		// cuando el servicio encuentre el metodo save, nos retorna la respuesta 200		
		when(service.save(any(Category.class))).thenReturn(
				new ResponseEntity<CategoryResponseRest>(HttpStatus.OK));
		
		ResponseEntity<CategoryResponseRest> response = categoryRestController.save(category);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
}
