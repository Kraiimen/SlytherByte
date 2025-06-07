package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataException.class)
    public ResponseEntity<String> handleDataException(){
        return ResponseEntity.internalServerError().body("Internal server error while processing data");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(){
        return ResponseEntity.internalServerError().body("Internal server error");
    }
}
