package com.erp.erp_system.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "returns")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @OneToMany(mappedBy = "returnEntity",
            cascade = CascadeType.ALL)
    private List<ReturnItemEntity> items;

    private Double refundAmount;
}

