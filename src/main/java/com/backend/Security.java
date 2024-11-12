package com.backend;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Deshabilita CSRF
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/auth/register", "/api/auth/login", "/api/users/all",
                 "/api/specialty/all", "/api/specialty/create", "/api/users/{id}","/api/doctor/all",
                 "/api/doctor/create","/api/specialty/doctors/{specialtyId}").permitAll()  // Permite acceso público a rutas específicas
                .anyRequest().authenticated()  // Requiere autenticación para otras rutas
            );
    
        return http.build();
    }

    

    // Bean para encriptar las contraseñas (opcional, pero recomendado)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}