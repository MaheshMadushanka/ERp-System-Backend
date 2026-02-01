package com.erp.erp_system.service;

import org.jspecify.annotations.Nullable;

import com.erp.erp_system.dto.ReturnRequestDTO;

public interface ReturnService {

    @Nullable
    Object processReturn(ReturnRequestDTO request);
    
}
