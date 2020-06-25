package com.alkham.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkham.entities.Product;
import com.alkham.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResources {
	
	@Autowired
	private ProductService service;
	
	@RequestMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product result = service.findById(id);
		
		return ResponseEntity.ok().body(result);
	}

}












