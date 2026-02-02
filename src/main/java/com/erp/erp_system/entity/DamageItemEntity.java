package com.erp.erp_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "damage_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DamageItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "damage_id")
    private DamageEntity damage;
}
