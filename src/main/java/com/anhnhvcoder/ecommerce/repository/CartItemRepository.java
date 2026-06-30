package com.anhnhvcoder.ecommerce.repository;

import com.anhnhvcoder.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
    Optional<CartItem> findBySizeId(Long sizeId);
    List<CartItem> findByCartId(Long cartId);
}
