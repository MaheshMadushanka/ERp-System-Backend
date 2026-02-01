package com.erp.erp_system.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "purchase_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Double costPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;
}
