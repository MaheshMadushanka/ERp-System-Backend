package com.erp.erp_system.service;

import com.erp.erp_system.dto.DamageRequestDTO;
import com.erp.erp_system.entity.DamageEntity;

public interface DamageService {
 public DamageEntity recordDamage(DamageRequestDTO request);
}