package com.anhnhvcoder.ecommerce.service;

import com.anhnhvcoder.ecommerce.model.Cart;

public interface CartItemService {

    Cart addItemToCart(Long cartId, int quantity, Long sizeId);
    void removeItemFromCart(Long itemId);
    void updateQuantity(Long itemId, int quantity);
}
