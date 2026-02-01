package com.erp.erp_system.repository;
import com.erp.erp_system.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
        extends JpaRepository<OrderEntity, Long> {
            
}
