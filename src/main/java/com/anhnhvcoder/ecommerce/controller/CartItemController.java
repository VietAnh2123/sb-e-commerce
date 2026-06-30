package com.anhnhvcoder.ecommerce.controller;

import com.anhnhvcoder.ecommerce.exception.ResourceNotFoundException;
import com.anhnhvcoder.ecommerce.model.Cart;
import com.anhnhvcoder.ecommerce.model.User;
import com.anhnhvcoder.ecommerce.response.ApiResponse;
import com.anhnhvcoder.ecommerce.service.CartItemService;
import com.anhnhvcoder.ecommerce.service.CartService;
import com.anhnhvcoder.ecommerce.service.UserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart-item")
@RequiredArgsConstructor
public class CartItemController {
    
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam("quantity") int quantity,
                                                     @RequestParam("sizeId") Long sizeId){
        try {
            User user = userService.getAuthenticatedUser();
            Cart cart = cartService.initializeNewCart(user);

            return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), cartItemService.addItemToCart(cart.getId(), quantity, sizeId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new ApiResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }catch (JwtException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(new ApiResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long itemId){
        cartItemService.removeItemFromCart(itemId);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Item Removed"));
    }

    @PutMapping("/update/{itemId}/{quantity}")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long itemId,
                                                          @PathVariable Integer quantity){
        try {
            cartItemService.updateQuantity(itemId, quantity);
            return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Item Updated"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(new ApiResponse(HttpStatus.CONFLICT.value(), e.getMessage()));
        }

    }

}
