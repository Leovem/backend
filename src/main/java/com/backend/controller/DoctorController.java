package com.backend.controller;

//import com.backend.model.Doctor;
import com.backend.dto.DoctorDTO;
import com.backend.model.Doctor;
import com.backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Punto de acceso para obtener todos los médicos
    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        System.out.println("Doctors being sent to frontend: " + doctors.toString());
        return doctors;
    }
    
    // Punto de acceso para crear un nuevo médico
    @PostMapping("/create")
public ResponseEntity<Map<String, String>> createDoctor(@RequestBody DoctorDTO doctorDTO) {
    Map<String, String> response = new HashMap<>();
    try {
        doctorService.createDoctor(doctorDTO); // Llama al servicio para registrar el médico
        response.put("message", "Médico creado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
        response.put("message", "Error al crear médico: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
}
