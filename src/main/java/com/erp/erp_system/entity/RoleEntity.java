package com.erp.erp_system.entity;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")

public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleID;

    @Column(nullable = false, unique = true, name = "role_name")
    private String roleName;
}   