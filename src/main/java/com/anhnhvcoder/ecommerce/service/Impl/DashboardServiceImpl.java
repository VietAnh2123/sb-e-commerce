package com.anhnhvcoder.ecommerce.service.Impl;

import com.anhnhvcoder.ecommerce.enums.OrderStatus;
import com.anhnhvcoder.ecommerce.repository.CategoryRepository;
import com.anhnhvcoder.ecommerce.repository.OrderRepository;
import com.anhnhvcoder.ecommerce.repository.ProductRepository;
import com.anhnhvcoder.ecommerce.repository.UserRepository;
import com.anhnhvcoder.ecommerce.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Map<String, Object> getDashboardData() {
        Map<String, Object> response = new HashMap<>();
        response.put("totalOrders", orderRepository.count());
        response.put("totalProducts", productRepository.count());
        response.put("totalUsers", userRepository.count());
        response.put("totalCategories", categoryRepository.count());
        response.put("totalRevenue", orderRepository.sumTotalAmountByStatus(OrderStatus.DELIVERED));
        return response;
    }
}
