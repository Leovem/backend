package com.backend.service;

import com.backend.model.Specialty;
import com.backend.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    // Método para obtener todas las especialidades
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    // Método para crear una nueva especialidad
    public void createSpecialty(Specialty specialty) {
        specialtyRepository.save(specialty);
    }

}
