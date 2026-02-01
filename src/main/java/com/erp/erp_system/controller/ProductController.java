package com.erp.erp_system.controller;

import com.erp.erp_system.dto.ProductRequestDTO;
import com.erp.erp_system.response.ApiResponse;
import com.erp.erp_system.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class ProductController {

        private final ProductService productService;

        // CREATE
        @PostMapping
        public ResponseEntity<ApiResponse<ProductRequestDTO>> createProduct(
                        @Valid @RequestBody ProductRequestDTO dto) {
              

                System.out.println("Received DTO: " + dto);
                log.info("Received product: {}", dto);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new ApiResponse<>(
                                                true,
                                                "Product created successfully",
                                                productService.createProduct(dto)));
        }

        // READ ALL
        @GetMapping
        public ResponseEntity<ApiResponse<List<ProductRequestDTO>>> getAllProducts() {

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Products fetched successfully",
                                                productService.getAllProducts()));
        }

        // READ BY ID
        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<ProductRequestDTO>> getProductById(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Product fetched successfully",
                                                productService.getProductById(id)));
        }

        // UPDATE
        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<ProductRequestDTO>> updateProduct(
                        @PathVariable Long id,
                        @Valid @RequestBody ProductRequestDTO dto) {

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Product updated successfully",
                                                productService.updateProduct(id, dto)));
        }

        // DELETE
        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteProduct(
                        @PathVariable Long id) {

                productService.deleteProduct(id);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Product deleted successfully", null));
        }
}
