package com.anhnhvcoder.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private List<ProductImageDTO> images;
}
