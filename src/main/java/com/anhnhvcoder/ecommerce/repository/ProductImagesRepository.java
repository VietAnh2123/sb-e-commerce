package com.anhnhvcoder.ecommerce.repository;

import com.anhnhvcoder.ecommerce.model.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long>{

    List<ProductImages> findByProductId(Long productId);
}
