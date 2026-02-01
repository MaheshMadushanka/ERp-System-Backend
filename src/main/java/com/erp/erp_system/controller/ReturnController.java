package com.erp.erp_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.erp_system.dto.ReturnRequestDTO;
import com.erp.erp_system.service.ReturnService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/returns")
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnService returnService;

    @PostMapping
    public ResponseEntity<?> returnItems(
            @RequestBody ReturnRequestDTO request) {

        return ResponseEntity.ok(
                returnService.processReturn(request));
    }
}

