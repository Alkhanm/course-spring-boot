package com.alkham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkham.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
