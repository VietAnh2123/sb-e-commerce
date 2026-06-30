package com.anhnhvcoder.ecommerce.repository;

import com.anhnhvcoder.ecommerce.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Optional<Brand> findByName(String name);

    boolean existsByNameIgnoreCase(String name);
}
