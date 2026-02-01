package com.erp.erp_system.service;

import com.erp.erp_system.dto.OrderRequestDTO;
import com.erp.erp_system.entity.OrderEntity;

public interface OrderService {
    
    public OrderEntity createOrder(OrderRequestDTO request);

}
