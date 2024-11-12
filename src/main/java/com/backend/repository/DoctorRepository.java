package com.backend.repository;

import com.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List; 

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Puedes agregar métodos personalizados si los necesitas, como buscar por `licenseNumber`.
    Doctor findByLicenseNumber(String licenseNumber);
    List<Doctor> findBySpecialty_SpecialtyId(Long specialtyId);

} 

