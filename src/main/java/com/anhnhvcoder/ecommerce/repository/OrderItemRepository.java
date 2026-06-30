package com.anhnhvcoder.ecommerce.repository;

import com.anhnhvcoder.ecommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
