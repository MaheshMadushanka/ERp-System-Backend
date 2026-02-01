package com.erp.erp_system.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    private Long id;
    @NotBlank(message = "Product name is required")
    private String name;

    private String productImageURL;

    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;

    private String sku;
    @NotNull(message = "Category ID is required")
    private long categoryId;

    @Column(name = "qr_code", unique = true)
    private String qrCode;

    @Column(name = "qr_image_url")
    private String qrImageURL;
}
