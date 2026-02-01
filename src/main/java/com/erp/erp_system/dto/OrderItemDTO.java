package com.erp.erp_system.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long productId;
    private Integer quantity;
    private Double price;
}
