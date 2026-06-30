package com.anhnhvcoder.ecommerce.service;

import com.anhnhvcoder.ecommerce.dto.OrderDTO;
import com.anhnhvcoder.ecommerce.dto.PaymentDTO;
import com.anhnhvcoder.ecommerce.enums.OrderStatus;
import com.anhnhvcoder.ecommerce.model.Order;
import com.anhnhvcoder.ecommerce.request.OrderRequest;
import com.anhnhvcoder.ecommerce.request.PaymentRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Order placeOrder(OrderRequest request);

    List<OrderDTO> getOrdersByUserId(OrderStatus status);

    Order cancelOrder(Long orderId);

    Page<OrderDTO> getAllOrders(int page, OrderStatus status);

    Order updateOrderStatus(Long orderId);

    Order confirmDelivered(Long orderId);

    PaymentDTO.PayOSResponse createPayOSPayment() throws Exception;

    Order executePaymentOrder(PaymentRequest request);

    List<OrderDTO> getNewestOrders();
}
