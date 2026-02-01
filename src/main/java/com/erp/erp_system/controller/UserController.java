package com.erp.erp_system.controller;
import com.erp.erp_system.dto.UserRequestDTO;
import com.erp.erp_system.dto.UserResponseDTO;
import com.erp.erp_system.response.ApiResponse;
import com.erp.erp_system.service.UserService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(
            @RequestBody UserRequestDTO user) {

        UserResponseDTO createdUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User created successfully", createdUser));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers() {

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Users fetched successfully",
                        userService.getAllUsers())
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(true, "User fetched successfully",
                        userService.getUserById(id))
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO user) {

        return ResponseEntity.ok(
                new ApiResponse<>(true, "User updated successfully",
                        userService.updateUser(id, user))
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "User deleted successfully", null)
        );
    }
}