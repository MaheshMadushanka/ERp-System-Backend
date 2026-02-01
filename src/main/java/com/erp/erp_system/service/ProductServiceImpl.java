package com.erp.erp_system.service;

import com.erp.erp_system.dto.ProductRequestDTO;
import com.erp.erp_system.entity.CategoryEntity;
import com.erp.erp_system.entity.ProductEntity;
import com.erp.erp_system.exception.BadRequestException;
import com.erp.erp_system.repository.CategoryRepository;
import com.erp.erp_system.repository.ProductRepository;
import com.erp.erp_system.utils.QRCodeGenerator;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

        private final ProductRepository productRepository;
        private final ModelMapper modelMapper;
        private final CategoryRepository categoryRepository;

        @Override
        public ProductRequestDTO createProduct(ProductRequestDTO dto) {
                if (productRepository.existsByName(dto.getName())) {
                        throw new BadRequestException("Product already exists");
                }
                if (productRepository.existsBySku(dto.getSku())) {
                        throw new BadRequestException("SKU already exists");
                }

                // 1️⃣ Get category
                CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                                .orElseThrow(() -> new RuntimeException("Category not found"));

                // 2️⃣ Save product FIRST (to get ID)
                ProductEntity product = new ProductEntity();
                product.setName(dto.getName());
                product.setDescription(dto.getDescription());
                product.setPrice(dto.getPrice());
                product.setQuantity(dto.getQuantity());
                product.setSku(dto.getSku());
                product.setProductImageURL(dto.getProductImageURL());
                product.setCategory(category);

                product = productRepository.save(product); // ID GENERATED HERE ✅

                // 3️⃣ Generate QR value
                String qrValue = "ERP:PRODUCT:" + product.getId();

                // 4️⃣ Generate QR image
                String qrFileName = "product-" + product.getId() + ".png";
                String qrPath = "src/main/resources/static/qrcodes/" + qrFileName;

                try {
                        QRCodeGenerator.generateQRCode(qrValue, qrPath);
                } catch (Exception e) {
                        throw new RuntimeException("Failed to generate QR Code", e);
                }

                // 5️⃣ Save QR data in DB
                product.setQrCode(qrValue);
                product.setQrImageURL("/qrcodes/" + qrFileName);

                productRepository.save(product);

                return modelMapper.map(product, ProductRequestDTO.class);
        }

        @Override
        public ProductRequestDTO updateProduct(Long id, ProductRequestDTO dto) {

                ProductEntity entity = productRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("Product not found"));

                entity.setName(dto.getName());
                entity.setProductImageURL(dto.getProductImageURL());
                entity.setDescription(dto.getDescription());
                entity.setPrice(dto.getPrice());
                entity.setQuantity(dto.getQuantity());

                return modelMapper.map(
                                productRepository.save(entity),
                                ProductRequestDTO.class);
        }

        @Override
        public ProductRequestDTO getProductById(Long id) {

                ProductEntity entity = productRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("Product not found"));

                return modelMapper.map(entity, ProductRequestDTO.class);
        }

        @Override
        public List<ProductRequestDTO> getAllProducts() {

                return productRepository.findAll()
                                .stream()
                                .map(product -> modelMapper.map(product, ProductRequestDTO.class))
                                .toList();
        }

        @Override
        public void deleteProduct(Long id) {
                ProductEntity product = productRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("Product not found"));

                if (product.getQuantity() > 0) {
                        throw new BadRequestException(
                                        "Cannot delete product. Stock quantity is " + product.getQuantity() + ".");
                }

                productRepository.delete(product);
        }
}
