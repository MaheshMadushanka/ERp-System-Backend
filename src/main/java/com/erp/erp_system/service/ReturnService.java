package com.erp.erp_system.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.erp.erp_system.dto.ReturnRequestDTO;

public interface ReturnService {

    @Nullable
    Object processReturn(ReturnRequestDTO request);

    @Nullable
    Object getReturnById(Long id);

    @Nullable
    List<ReturnRequestDTO> getAllReturns();
    
}
