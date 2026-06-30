package com.anhnhvcoder.ecommerce.repository;

import com.anhnhvcoder.ecommerce.enums.OrderStatus;
import com.anhnhvcoder.ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByUserIdAndStatus(Long userId, OrderStatus status);

    Page<Order> findByStatusOrderByOrderDateDesc(Pageable pageable, OrderStatus status);

    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.status = ?1")
    BigDecimal sumTotalAmountByStatus(OrderStatus status);

    Optional<Order> findByOrderCode(Long orderCode);

    List<Order> findTop3ByOrderByOrderDateDesc();

}
