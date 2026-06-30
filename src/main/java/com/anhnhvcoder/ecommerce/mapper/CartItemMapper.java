package com.anhnhvcoder.ecommerce.mapper;

import com.anhnhvcoder.ecommerce.dto.CartItemDTO;
import com.anhnhvcoder.ecommerce.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemDTO toCartItemDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setItemId(cartItem.getId());
        cartItemDTO.setSizeId(cartItem.getSize().getId());
        cartItemDTO.setCartId(cartItem.getCart().getId());
        cartItemDTO.setProductId(cartItem.getSize().getProduct().getId());
        cartItemDTO.setProductName(cartItem.getSize().getProduct().getName());
        cartItemDTO.setProductPrice(cartItem.getSize().getProduct().getPrice());
        cartItemDTO.setUnitPrice(cartItem.getUnitPrice());
        cartItemDTO.setTotalPrice(cartItem.getTotalPrice());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setImageUrl(cartItem.getSize().getProduct().getImages().get(0).getUrl());
        cartItemDTO.setSizeName(cartItem.getSize().getSizeName());
        cartItemDTO.setDescription(cartItem.getSize().getProduct().getDescription());
        return cartItemDTO;
    }
}
