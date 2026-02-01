package com.erp.erp_system.repository;

import com.erp.erp_system.entity.ProductEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByName(String name);
    boolean existsBySku(String sku);
}
