package com.anhnhvcoder.ecommerce.repository;

import com.anhnhvcoder.ecommerce.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Long> {
    
    Size findBySizeNameAndProductId(String sizeName, Long productId);

    Size findBySizeName(String sizeName);
}
