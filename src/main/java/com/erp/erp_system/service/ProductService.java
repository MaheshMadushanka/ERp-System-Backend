package com.erp.erp_system.service;

import com.erp.erp_system.dto.ProductRequestDTO;

import java.util.List;

public interface ProductService {

    ProductRequestDTO createProduct(ProductRequestDTO dto);

    ProductRequestDTO updateProduct(Long id, ProductRequestDTO dto);

    ProductRequestDTO getProductById(Long id);

    List<ProductRequestDTO> getAllProducts();

    void deleteProduct(Long id);
}
