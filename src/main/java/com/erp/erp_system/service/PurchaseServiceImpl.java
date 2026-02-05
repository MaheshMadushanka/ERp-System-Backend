package com.erp.erp_system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.erp.erp_system.dto.PurchaseItemDTO;
import com.erp.erp_system.dto.PurchaseRequestDTO;
import com.erp.erp_system.entity.ProductEntity;
import com.erp.erp_system.entity.PurchaseEntity;
import com.erp.erp_system.entity.PurchaseItemEntity;
import com.erp.erp_system.repository.ProductRepository;
import com.erp.erp_system.repository.PurchaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final ProductRepository productRepo;
    private final PurchaseRepository purchaseRepo;

    public PurchaseEntity addStock(PurchaseRequestDTO request) {

        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setPurchaseDate(LocalDateTime.now());

        List<PurchaseItemEntity> itemEntities = new ArrayList<>();
        double totalCost = 0;

        for (PurchaseItemDTO dto : request.getItems()) {

            ProductEntity product = productRepo.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // ✅ Increase stock
            product.setQuantity(
                    product.getQuantity() + dto.getQuantity());
            productRepo.save(product);

            PurchaseItemEntity item = new PurchaseItemEntity();
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());
            item.setCostPrice(dto.getCostPrice());
            item.setPurchase(purchase);

            totalCost += dto.getQuantity() * dto.getCostPrice();
            itemEntities.add(item);
        }

        purchase.setItems(itemEntities);
        purchase.setTotalCost(totalCost);

        return purchaseRepo.save(purchase);
    }
}

