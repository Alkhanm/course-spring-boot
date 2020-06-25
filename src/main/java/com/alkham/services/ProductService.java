package com.alkham.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkham.entities.Product;
import com.alkham.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	
	public List<Product> findAll(){
		
		return repository.findAll();
	}

	public Product findById(Long id){
		
		Optional<Product> result = repository.findById(id);
		
		return result.get();
	}
}
