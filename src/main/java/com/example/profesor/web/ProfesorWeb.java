package com.example.profesor.web;

import com.example.profesor.entities.Profesor;
import com.example.profesor.exception.ObjectNotFoundException;
import com.example.profesor.services.IProfesorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProfesorWeb {
    @Autowired
    private IProfesorService profesorService;

    @GetMapping("/profesores")
    public ResponseEntity<List<Profesor>> findAllProfesor(){
        return ResponseEntity.ok(this.profesorService.findAllProfesor());
    }

    @GetMapping("/profesor/{id}")
    public ResponseEntity<Profesor> findProfesor(@PathVariable Long id) throws ObjectNotFoundException {
        return ResponseEntity.ok(this.profesorService.findProfesor(id));
    }

    @PostMapping("/profesor")
    public ResponseEntity<?> saveProfesor(@RequestBody @Valid Profesor profesor){
        Map<String, Object> response = new HashMap<>();
        Profesor profesorNew = this.profesorService.saveProfesor(profesor);

        response.put("mensaje","Se ha aguardado");
        response.put("profesor",profesorNew);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/profesor/{id}")
    public ResponseEntity<?> updateProfesor(@RequestBody @Valid Profesor profesor, @PathVariable Long id) throws ObjectNotFoundException {
        Map<String, Object> response = new HashMap<>();
        Profesor profesorActual = this.profesorService.findProfesor(id);
        profesor.setId(profesorActual.getId());
        profesorActual = profesor;

        Profesor profesorUpdate = this.profesorService.saveProfesor(profesorActual);
        response.put("mensaje","Se ha actualizado");
        response.put("profesor", profesorUpdate);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<?> deleteProfesor(@PathVariable Long id) throws ObjectNotFoundException {
        Map<String, Object> response = new HashMap<>();
        this.profesorService.deleteProfesor(id);
        response.put("mensaje","Se ha eliminado");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }




}
