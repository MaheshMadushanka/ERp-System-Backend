package com.erp.erp_system.dto;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private List<OrderItemDTO> items;
    private Double totalAmount;
    private String paymentMethod;
}
