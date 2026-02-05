package com.erp.erp_system.service;

import java.util.List;

import com.erp.erp_system.dto.OrderRequestDTO;
import com.erp.erp_system.entity.OrderEntity;

public interface OrderService {
    OrderEntity createOrder(OrderRequestDTO request);
    OrderEntity getOrderById(Long id);
    List<OrderRequestDTO> getAllOrders();
}

