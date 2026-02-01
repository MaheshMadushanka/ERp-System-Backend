package com.erp.erp_system.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItemDTO {
    private Long productId;
    private Integer quantity;
    private Double costPrice;
}
