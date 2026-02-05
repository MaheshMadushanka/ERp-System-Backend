package com.erp.erp_system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.erp_system.dto.ReturnRequestDTO;
import com.erp.erp_system.response.ApiResponse;
import com.erp.erp_system.service.ReturnService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/returns")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class ReturnController {

    private final ReturnService returnService;

    @PostMapping
    public ResponseEntity<?> returnItems(@RequestBody ReturnRequestDTO request) {

        return ResponseEntity.ok(
                returnService.processReturn(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReturnById(@PathVariable Long id) {

        return ResponseEntity.ok(
                returnService.getReturnById(id));
}
    @GetMapping
    public ResponseEntity<ApiResponse<List<ReturnRequestDTO>>> getAllReturns() {

        return ResponseEntity.ok(
            new ApiResponse<>(
                true,
                "Returns fetched successfully",
                returnService.getAllReturns()));
}}
