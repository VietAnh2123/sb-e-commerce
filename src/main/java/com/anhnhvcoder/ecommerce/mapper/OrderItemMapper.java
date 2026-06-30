package com.anhnhvcoder.ecommerce.mapper;

import com.anhnhvcoder.ecommerce.dto.OrderItemDTO;
import com.anhnhvcoder.ecommerce.dto.SizeMapper;
import com.anhnhvcoder.ecommerce.model.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

    private final SizeMapper sizeMapper;

    public Set<OrderItemDTO> toOrderItemDTOSet(Set<OrderItem> orderItems) {
        return orderItems.stream().map((item) -> {
            OrderItemDTO dto = new OrderItemDTO();
            dto.setId(item.getId());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getPrice());
            dto.setSizeDTO(sizeMapper.toSizeDTO(item.getSize()));
            return dto;
        }).collect(Collectors.toSet());
    }
}
