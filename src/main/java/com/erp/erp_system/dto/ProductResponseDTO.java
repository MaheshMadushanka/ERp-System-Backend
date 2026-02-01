package com.erp.erp_system.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String productImageURL;
    private String description;
    private Double price;
    private Integer quantity;
    private String sku;
    private String categoryName;
    private String qrCode;
    private String qrImageURL;
}
