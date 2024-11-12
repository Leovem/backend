package com.backend.controller;

import com.backend.service.UserService;
import com.backend.model.User;
import com.backend.dto.LoginRequest;
import com.backend.dto.AuthResponse;
import java.util.Map;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            boolean isValid = userService.validateCredentials(loginRequest.getEmail(), loginRequest.getPassword());
            if (isValid) {
                // Obtener el rol del usuario
                String role = userService.getRoleByEmail(loginRequest.getEmail()).orElse("Unknown");

                // Crear la respuesta
                AuthResponse authResponse = new AuthResponse("Inicio de sesión exitoso", role);
                return ResponseEntity.ok(authResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new AuthResponse("Credenciales incorrectas", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse("Error en el servidor", null));
        }
    }

    // Nuevo método para registrar usuario
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        try {
            userService.UserRegister(user);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario registrado exitosamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            // return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


}
