package com.alkham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkham.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
