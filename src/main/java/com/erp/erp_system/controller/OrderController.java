package com.erp.erp_system.controller;
import com.erp.erp_system.dto.OrderRequestDTO;
import com.erp.erp_system.entity.OrderEntity;
import com.erp.erp_system.service.OrderService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody OrderRequestDTO request) {

        OrderEntity order = orderService.createOrder(request);

        return ResponseEntity.ok(order);
    }
}

