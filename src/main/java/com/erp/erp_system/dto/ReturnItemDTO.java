package com.erp.erp_system.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnItemDTO {
    private Long productId;
    private Integer quantity;
}