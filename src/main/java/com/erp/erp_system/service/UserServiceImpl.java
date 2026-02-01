package com.erp.erp_system.service;

import com.erp.erp_system.dto.UserRequestDTO;
import com.erp.erp_system.dto.UserResponseDTO;
import com.erp.erp_system.entity.UserEntity;
import com.erp.erp_system.exception.BadRequestException;
import com.erp.erp_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;


import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        return modelMapper.map(
                userRepository.save(entity),
                UserResponseDTO.class
        );
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {

        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));

        // map non-null fields only (simple version)
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return modelMapper.map(
                userRepository.save(entity),
                UserResponseDTO.class
        );
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));

        return modelMapper.map(entity, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .toList();
    }

    @Override
    public void deleteUser(Long id) {

        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));

        userRepository.delete(entity);
    }
}