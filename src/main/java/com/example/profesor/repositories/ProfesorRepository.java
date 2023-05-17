package com.example.profesor.repositories;

import com.example.profesor.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor,Long> {
}
