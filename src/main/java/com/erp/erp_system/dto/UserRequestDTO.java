package com.erp.erp_system.dto;

import com.erp.erp_system.entity.RoleEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDTO {

    private Long userID;
    @NotBlank(message = "Username cannot be null")
    private String username;
    @NotBlank(message = "Password cannot be null")
    private String password;
    @NotBlank(message = "Email cannot be null")
    private String email;
    @NotBlank(message = "Address cannot be null")
    private String address;
    @NotBlank(message = "Role cannot be null")
    private RoleEntity role;
}
