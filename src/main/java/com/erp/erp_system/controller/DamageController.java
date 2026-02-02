package com.erp.erp_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.erp_system.dto.DamageRequestDTO;
import com.erp.erp_system.service.DamageService;

import lombok.*;

@RestController
@RequestMapping("/api/damages")
@RequiredArgsConstructor
public class DamageController {

    private final DamageService damageService;

    @PostMapping
    public ResponseEntity<?> recordDamage(
            @RequestBody DamageRequestDTO request) {

        return ResponseEntity.ok(
                damageService.recordDamage(request));
    }
}

