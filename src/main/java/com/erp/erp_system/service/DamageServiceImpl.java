package com.erp.erp_system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.erp.erp_system.dto.DamageItemDTO;
import com.erp.erp_system.dto.DamageRequestDTO;
import com.erp.erp_system.entity.DamageEntity;
import com.erp.erp_system.entity.DamageItemEntity;
import com.erp.erp_system.entity.ProductEntity;
import com.erp.erp_system.repository.DamageRepository;
import com.erp.erp_system.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class   DamageServiceImpl implements DamageService {

    private final ProductRepository productRepo;
    private final DamageRepository damageRepo;

    public DamageEntity recordDamage(DamageRequestDTO request) {

        DamageEntity damage = new DamageEntity();
        damage.setDamageDate(LocalDateTime.now());
        damage.setReason(request.getReason());

        List<DamageItemEntity> items = new ArrayList<>();

        for (DamageItemDTO dto : request.getItems()) {

            ProductEntity product = productRepo.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < dto.getQuantity()) {
                throw new RuntimeException("Not enough stock");
            }

            // ✅ reduce stock
            product.setQuantity(
                    product.getQuantity() - dto.getQuantity());

            DamageItemEntity item = new DamageItemEntity();
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());
            item.setDamage(damage);

            items.add(item);
        }

        damage.setItems(items);

        return damageRepo.save(damage);
    }
}
