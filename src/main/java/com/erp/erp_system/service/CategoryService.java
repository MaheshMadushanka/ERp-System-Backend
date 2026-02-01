package com.erp.erp_system.service;

import com.erp.erp_system.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO dto);

    CategoryDTO updateCategory(Long id, CategoryDTO dto);

    CategoryDTO getCategoryById(Long id);

    List<CategoryDTO> getAllCategories();

    void deleteCategory(Long id);
}
