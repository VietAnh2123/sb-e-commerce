package com.anhnhvcoder.ecommerce.service;

import com.anhnhvcoder.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    void deleteCategory(Long id);
}
