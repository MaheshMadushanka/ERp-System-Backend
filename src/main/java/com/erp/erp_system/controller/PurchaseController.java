package com.erp.erp_system.controller;
import com.erp.erp_system.dto.PurchaseRequestDTO;
import com.erp.erp_system.entity.PurchaseEntity;
import com.erp.erp_system.response.ApiResponse;

import lombok.RequiredArgsConstructor;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.erp_system.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<ApiResponse<PurchaseEntity>> addStock(
            @RequestBody PurchaseRequestDTO request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                    true, 
                    "Stock added successfully", 
                    purchaseService.addStock(request)));
    }
}

