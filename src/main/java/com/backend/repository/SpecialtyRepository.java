package com.backend.repository;

import com.backend.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    // No es necesario implementar métodos adicionales aquí a menos que necesites consultas personalizadas.
}
