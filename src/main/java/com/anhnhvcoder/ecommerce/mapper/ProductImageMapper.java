package com.anhnhvcoder.ecommerce.mapper;

import com.anhnhvcoder.ecommerce.dto.ProductImageDTO;
import com.anhnhvcoder.ecommerce.model.ProductImages;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductImageMapper {

    public List<ProductImageDTO> toProductImageDTO(List<ProductImages> productImages) {

        return productImages.stream().map((image) -> {
            ProductImageDTO productImageDTO = new ProductImageDTO();
            productImageDTO.setId(image.getId());
            productImageDTO.setUrl(image.getUrl());
            return productImageDTO;
        }).toList();
    }
}
