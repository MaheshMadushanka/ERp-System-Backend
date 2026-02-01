package com.erp.erp_system.service;

import com.erp.erp_system.dto.UserRequestDTO;
import com.erp.erp_system.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO user) ;

    UserResponseDTO updateUser(Long id, UserRequestDTO user);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();

    void deleteUser(Long id);
}
