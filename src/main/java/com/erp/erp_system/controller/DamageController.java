package com.erp.erp_system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.erp_system.dto.DamageRequestDTO;
import com.erp.erp_system.entity.DamageEntity;
import com.erp.erp_system.response.ApiResponse;
import com.erp.erp_system.service.DamageService;

import lombok.*;

@RestController
@RequestMapping("/api/damages")
@RequiredArgsConstructor
public class DamageController {

    private final DamageService damageService;

    @PostMapping
    public ResponseEntity<ApiResponse<DamageEntity>> recordDamage(
            @RequestBody DamageRequestDTO request) {

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Damage recorded successfully",
                damageService.recordDamage(request)));
    }
     @GetMapping
    public ResponseEntity<ApiResponse<List<DamageEntity>>> getAllDamages() {

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Damages fetched successfully",
                damageService.getAllDamages()));
    }
}

