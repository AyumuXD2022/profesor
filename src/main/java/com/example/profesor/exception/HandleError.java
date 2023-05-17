package com.example.profesor.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandleError {



    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public Map<String, Object>handlessDbExceptio(DataAccessException ex){
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("mensaje","Error al realizar una operacion en la bases de datos");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object>handlessValidationExceptio(MethodArgumentNotValidException ex){
        Map<String, Object> errorMap = new HashMap<>();
        List<String> errors = ex.getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());
        errorMap.put("errors",errors);
        errorMap.put("mensaje","Llena los capos restantes o escribe de la manera correcta");
        return errorMap;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public Map<String, Object>handlessObjectExceptio(ObjectNotFoundException ex){
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("mensaje",ex.getMessage());
        return errorMap;
    }
}
