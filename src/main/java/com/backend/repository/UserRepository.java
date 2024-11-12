package com.backend.repository;

import com.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional; 

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(String role); // Método para encontrar usuarios por rol
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
