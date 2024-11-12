package com.backend.controller;

import com.backend.model.Doctor;
import com.backend.model.Specialty;
import com.backend.service.DoctorService;
import com.backend.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/specialty")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private DoctorService doctorService;

    // Punto de acceso para obtener todas las especialidades
    @GetMapping("/all")
    public List<Specialty> getAllSpecialties() {
        return specialtyService.getAllSpecialties();
    }
    
    // Punto de acceso para crear una nueva especialidad
     @PostMapping("/create")
    public ResponseEntity<Map<String, String>> create(@RequestBody Specialty specialty) {
        try {
            specialtyService.createSpecialty(specialty); // Llama al servicio para registrar al usuario
            Map<String, String> response = new HashMap<>();
            response.put("message", "Especialidad creada exitosamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            //return ResponseEntity.status(HttpStatus.CREATED).body("Especialidad registrada exitosamente");
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar Especialidad: " + e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al crear especialidad: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

     // Método para obtener médicos por especialidad
    @GetMapping("/doctors/{specialtyId}")
     public List<Doctor> getDoctorsBySpecialty(@PathVariable("specialtyId") Long specialtyId) {
      return doctorService.getDoctorsBySpecialty(specialtyId);  // Llamar al servicio para obtener médicos por especialidad
    }
}
