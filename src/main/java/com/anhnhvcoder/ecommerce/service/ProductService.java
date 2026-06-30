package com.anhnhvcoder.ecommerce.service;

import com.anhnhvcoder.ecommerce.model.Brand;
import com.anhnhvcoder.ecommerce.model.Product;
import com.anhnhvcoder.ecommerce.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product addProduct(String name,
                       String brand,
                       String description,
                       BigDecimal price,
                       int inventory,
                       String categoryName,
                       String sizeName,
                       MultipartFile[] images) throws IOException;

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void deleteProduct(Long id);

    Product updateProduct(Long id,
                          String name,
                          String brand,
                          String description,
                          BigDecimal price,
                          int inventory,
                          Long categoryId,
                          Long sizeId
    ) throws IOException;

    List<Product> findByBrand(String brand);

    List<Product> findByCategory(Long categoryId);

    Long countProducts();

    Size addMoreSize(Long productId, String sizeName, int quantity);

    List<Product> getNewArrivalProducts();

    List<Product> getProductByName(String name);

    List<Brand> getAllBrand();

    int getInventoryBySizeId(Long sizeId);

    Size getProductBySizeId(Long sizeId);

    Size editSizeInventory(Long sizeId, int quantity);

    Page<Product> getProductsByPage(Pageable pageable);
}
