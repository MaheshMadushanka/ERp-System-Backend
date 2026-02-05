package com.erp.erp_system.dto;

import java.util.List;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DamageRequestDTO {

    private String reason;
    private List<DamageItemDTO> items;

}
