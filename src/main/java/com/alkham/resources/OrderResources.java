package com.alkham.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkham.entities.Order;
import com.alkham.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResources {
	
	@Autowired
	private OrderService service;
	
	@RequestMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order result = service.findById(id);
		
		return ResponseEntity.ok().body(result);
	}

}












