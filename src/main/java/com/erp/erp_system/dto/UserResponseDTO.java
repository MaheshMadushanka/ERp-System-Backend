package com.erp.erp_system.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserResponseDTO {

    private Long userID;
    private String username;
    private String email;
    private String address;
    private String role;
}
