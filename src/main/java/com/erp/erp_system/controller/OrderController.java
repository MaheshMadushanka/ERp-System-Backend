package com.erp.erp_system.controller;

import com.erp.erp_system.dto.OrderRequestDTO;
import com.erp.erp_system.entity.OrderEntity;
import com.erp.erp_system.service.OrderService;

import lombok.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.erp.erp_system.response.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderEntity>> createOrder(
            @RequestBody OrderRequestDTO request) {

        return ResponseEntity.ok(new ApiResponse<>(
            true,
            "Order created successfully",
            orderService.createOrder(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderEntity>> getOrderById(
            @PathVariable Long id) {

        return ResponseEntity.ok(new ApiResponse<>(
            true,
            "Order fetched successfully",
            orderService.getOrderById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderRequestDTO>>> getAllOrders() {

        return ResponseEntity.ok(new ApiResponse<>(
            true,
            "Orders fetched successfully",
            orderService.getAllOrders()));
    }
}
