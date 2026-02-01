package com.erp.erp_system.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "return_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "return_id")
    private ReturnEntity returnEntity;
}
