package com.alkham.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkham.entities.Order;
import com.alkham.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	
	public List<Order> findAll(){
		
		return repository.findAll();
	}

	public Order findById(Long id){
		
		Optional<Order> result = repository.findById(id);
		
		return result.get();
	}
}
