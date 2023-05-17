package com.example.profesor.services;

import com.example.profesor.entities.Profesor;
import com.example.profesor.exception.ObjectNotFoundException;

import java.util.List;

public interface IProfesorService {
    List<Profesor> findAllProfesor();

    Profesor findProfesor(Long id) throws ObjectNotFoundException;

    Profesor saveProfesor(Profesor profesor);

    void deleteProfesor(Long id) throws ObjectNotFoundException;
}
