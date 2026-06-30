package com.anhnhvcoder.ecommerce.service;

import com.anhnhvcoder.ecommerce.dto.CartDTO;
import com.anhnhvcoder.ecommerce.model.Cart;
import com.anhnhvcoder.ecommerce.model.User;

public interface CartService {

    CartDTO getCart();
    Cart initializeNewCart(User user);
    Cart getCartByUserId(Long userId);
    void clearCart();
    Integer getCartCount();
}