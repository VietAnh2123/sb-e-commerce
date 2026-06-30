package com.anhnhvcoder.ecommerce.dto;

import com.anhnhvcoder.ecommerce.mapper.ProductMapper;
import com.anhnhvcoder.ecommerce.model.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SizeMapper {

    private final ProductMapper productMapper;

    public SizeDTO toSizeDTO(Size size) {
        SizeDTO sizeDTO = new SizeDTO();
        sizeDTO.setId(size.getId());
        sizeDTO.setSizeName(size.getSizeName());
        sizeDTO.setQuantity(size.getQuantity());
        sizeDTO.setProduct(productMapper.toProductDTO(size.getProduct()));
        return sizeDTO;
    }
}
