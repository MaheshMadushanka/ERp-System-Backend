package com.erp.erp_system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.erp.erp_system.dto.ReturnItemDTO;
import com.erp.erp_system.dto.ReturnRequestDTO;
import com.erp.erp_system.entity.OrderEntity;
import com.erp.erp_system.entity.ProductEntity;
import com.erp.erp_system.entity.ReturnEntity;
import com.erp.erp_system.entity.ReturnItemEntity;
import com.erp.erp_system.repository.OrderRepository;
import com.erp.erp_system.repository.ProductRepository;
import com.erp.erp_system.repository.ReturnRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReturnServiceImpl {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final ReturnRepository returnRepo;

    public ReturnEntity processReturn(ReturnRequestDTO request) {

        OrderEntity order = orderRepo.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setOrder(order);
        returnEntity.setReturnDate(LocalDateTime.now());

        List<ReturnItemEntity> returnItems = new ArrayList<>();
        double refundTotal = 0;

        for (ReturnItemDTO dto : request.getItems()) {

            ProductEntity product = productRepo.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // ✅ Add stock back
            product.setQuantity(
                    product.getQuantity() + dto.getQuantity());
            productRepo.save(product);

            ReturnItemEntity item = new ReturnItemEntity();
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());
            item.setReturnEntity(returnEntity);

            refundTotal += product.getPrice() * dto.getQuantity();
            returnItems.add(item);
        }

        returnEntity.setItems(returnItems);
        returnEntity.setRefundAmount(refundTotal);

        return returnRepo.save(returnEntity);
    }
}
