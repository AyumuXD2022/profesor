package com.example.profesor.services;

import com.example.profesor.entities.Profesor;
import com.example.profesor.exception.ObjectNotFoundException;
import com.example.profesor.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfesorService implements IProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Profesor> findAllProfesor(){
        return this.profesorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Profesor findProfesor(Long id) throws ObjectNotFoundException {
        Profesor profesor =  this.profesorRepository.findById(id).orElse(null);
        if(profesor != null){
            return profesor;
        }else {
            throw new ObjectNotFoundException("Profesor con id " + id + " no se encuenta en la bases de datos");
        }
    }

    @Override
    @Transactional
    public Profesor saveProfesor(Profesor profesor){
        return this.profesorRepository.save(profesor);
    }
    @Override
    @Transactional
    public void deleteProfesor(Long id) throws ObjectNotFoundException {
        Profesor profesor = this.profesorRepository.findById(id).orElse(null);
        if(profesor != null){
            this.profesorRepository.delete(profesor);
        }else {
            throw new ObjectNotFoundException("Profesor con id " + id + " no se encuenta en la bases de datos");
        }

    }


}
