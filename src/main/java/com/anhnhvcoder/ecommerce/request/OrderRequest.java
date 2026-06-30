package com.anhnhvcoder.ecommerce.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private BigDecimal totalPrice;
    private String orderAddress;
    private Set<OrderItemRequest> items;
    private String paymentMethod;

}
