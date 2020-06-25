package com.alkham.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkham.entities.Category;
import com.alkham.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResources {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category result = service.findById(id);
		
		return ResponseEntity.ok().body(result);
	}

}












