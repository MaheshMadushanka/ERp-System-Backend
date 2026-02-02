package com.erp.erp_system.entity;

import java.time.LocalDateTime;
import java.util.List;
    
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "damages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DamageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime damageDate;

    private String reason;

    @OneToMany(mappedBy = "damage",
            cascade = CascadeType.ALL)
    private List<DamageItemEntity> items;
}

