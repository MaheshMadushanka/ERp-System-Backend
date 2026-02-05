package com.erp.erp_system.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.parameters.P;

import com.erp.erp_system.dto.PurchaseRequestDTO;
import com.erp.erp_system.entity.PurchaseEntity;

public interface PurchaseService {

    @Nullable
    PurchaseEntity addStock(PurchaseRequestDTO request);
    
}
