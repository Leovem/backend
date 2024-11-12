package com.backend.service;

import com.backend.model.User;
import com.backend.repository.UserRepository; 

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Para encriptar contraseñas
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private PasswordEncoder passwordEncoder; 

    // Método para guardar un usuario
    public void saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public boolean validateCredentials(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Comparar la contraseña cifrada
            return passwordEncoder.matches(password, user.getPassword());
        } else {
            return false;
        }
    }

      // Método para registrar un nuevo usuario
      public void UserRegister(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword); 
        userRepository.save(user); 
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Método para obtener todos los pacientes
    public List<User> findAllPatients() {
        return userRepository.findByRole("PATIENT"); 
    }

    // Método para obtener todos los médicos
    public List<User> findAllDoctors() {
        return userRepository.findByRole("DOCTOR"); 
    }

    // Método para obtener todos los administradores
    public List<User> findAllAdministrators() {
        return userRepository.findByRole("ADMIN"); 
    }

        // Método para encontrar un usuario por nombre de usuario
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

      // Método para obtener el rol de un usuario
    public Optional<String> getRoleByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(User::getRole); // Devuelve el rol si el usuario existe
    }
    

    // Método para actualizar un usuario
    public User updateUser(Long id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Actualiza los campos que desees
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setRole(userDetails.getRole());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }

    // Método para eliminar un usuario
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);  // Buscar el usuario por ID
        if (user.isPresent()) {
            return user.get();  // Si el usuario existe, devolverlo
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);  // Lanzar excepción si no se encuentra
        }
    }

}
