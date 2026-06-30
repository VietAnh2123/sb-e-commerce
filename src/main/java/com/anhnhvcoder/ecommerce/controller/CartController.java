package com.anhnhvcoder.ecommerce.controller;

import com.anhnhvcoder.ecommerce.exception.ResourceNotFoundException;
import com.anhnhvcoder.ecommerce.response.ApiResponse;
import com.anhnhvcoder.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/my-cart")
    public ResponseEntity<ApiResponse> getCart(){
        try {
            return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), cartService.getCart()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new ApiResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse> getCartItemQuantity(){
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), cartService.getCartCount()));
    }

}
