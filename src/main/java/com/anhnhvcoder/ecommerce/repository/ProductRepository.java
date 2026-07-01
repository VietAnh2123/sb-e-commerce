package com.anhnhvcoder.ecommerce.repository;

import com.anhnhvcoder.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findAllByOrderByIdDesc(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> findByName(String name);

    @EntityGraph(attributePaths = {"brand", "category"})
    @Query("""
            SELECT p
                FROM Product p
                JOIN FETCH p.brand b
                JOIN FETCH p.category c
                WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
                  AND LOWER(c.name) LIKE LOWER(CONCAT('%', :categoryName, '%'))
                  AND LOWER(b.name) LIKE LOWER(CONCAT('%', :brandName, '%'))
            """)
    Page<Product> searchProducts(Pageable pageable, String name, String categoryName, String brandName);

}
