package com.alkham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alkham.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
