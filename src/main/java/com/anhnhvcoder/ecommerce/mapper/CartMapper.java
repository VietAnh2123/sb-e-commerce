package com.anhnhvcoder.ecommerce.mapper;

import com.anhnhvcoder.ecommerce.dto.CartDTO;
import com.anhnhvcoder.ecommerce.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getId());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setItems(cart.getCartItems().stream().map(cartItemMapper::toCartItemDTO).collect(Collectors.toList()));
        cartDTO.setTotalItem(cart.getTotalItems());
        return cartDTO;
    }
}
