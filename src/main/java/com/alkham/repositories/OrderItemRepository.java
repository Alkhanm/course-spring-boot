package com.alkham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alkham.entities.Order;
import com.alkham.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
