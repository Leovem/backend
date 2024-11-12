package com.backend.service;

import com.backend.dto.DoctorDTO;
import com.backend.model.Doctor;
import com.backend.model.User;
import com.backend.model.Specialty;
import com.backend.repository.DoctorRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.SpecialtyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    // Método para obtener todos los doctores
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Método para crear un doctor
    public Doctor createDoctor(DoctorDTO doctorDTO) {
    User user = userRepository.findById(doctorDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + doctorDTO.getUserId()));
    Specialty specialty = specialtyRepository.findById(doctorDTO.getSpecialtyId())
            .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con ID: " + doctorDTO.getSpecialtyId()));
        

    Doctor doctor = new Doctor();
    doctor.setUser(user);
    doctor.setSpecialty(specialty);
    doctor.setLicenseNumber(doctorDTO.getLicenseNumber());

    return doctorRepository.save(doctor);
}


    // Método para buscar un doctor por ID
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }


    // Método para eliminar un doctor
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + id));
        doctorRepository.delete(doctor);
    }

    // Método para obtener médicos por especialidad
    public List<Doctor> getDoctorsBySpecialty(Long specialtyId) {
        return doctorRepository.findBySpecialty_SpecialtyId(specialtyId);  // Llamar al repositorio para buscar médicos por especialidad
    }
}
