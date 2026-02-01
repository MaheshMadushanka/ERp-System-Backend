package com.erp.erp_system.service;

import com.erp.erp_system.dto.CategoryDTO;
import com.erp.erp_system.entity.CategoryEntity;
import com.erp.erp_system.exception.BadRequestException;
import com.erp.erp_system.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {

        if (categoryRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Category already exists");
        }

        CategoryEntity entity = modelMapper.map(dto, CategoryEntity.class);
        CategoryEntity saved = categoryRepository.save(entity);

        return modelMapper.map(saved, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {

        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Category not found"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return modelMapper.map(
                categoryRepository.save(entity),
                CategoryDTO.class
        );
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {

        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Category not found"));

        return modelMapper.map(entity, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(cat -> modelMapper.map(cat, CategoryDTO.class))
                .toList();
    }

    @Override
    public void deleteCategory(Long id) {

        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Category not found"));

        categoryRepository.delete(entity);
    }
}
