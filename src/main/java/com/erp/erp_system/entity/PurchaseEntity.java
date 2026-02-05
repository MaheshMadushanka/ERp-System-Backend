package com.erp.erp_system.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "purchases")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime purchaseDate;

    @OneToMany(mappedBy = "purchase",
            cascade = CascadeType.ALL)
    private List<PurchaseItemEntity> items;

    private Double totalCost;
}
