package com.erp.erp_system.dto;
import lombok.*;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDTO {
    private List<PurchaseItemDTO> items;
}

