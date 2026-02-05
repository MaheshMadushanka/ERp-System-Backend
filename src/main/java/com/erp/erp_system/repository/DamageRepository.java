package com.erp.erp_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.erp_system.entity.DamageEntity;
@Repository
public interface DamageRepository extends JpaRepository<DamageEntity, Long> {

}
