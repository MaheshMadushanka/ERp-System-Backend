package com.erp.erp_system.dto;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRequestDTO {

    private Long orderId;
    private List<ReturnItemDTO> items;
}