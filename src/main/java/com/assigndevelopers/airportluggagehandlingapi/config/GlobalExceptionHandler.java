package com.assigndevelopers.airportluggagehandlingapi.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "error", exception.getMessage()
                ));
    }

    // For Post Request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ){
        var errors = new HashMap<String, String>();
        exception
                .getAllErrors()
                .forEach(
                        error -> {
                            var fieldName = ((FieldError) error).getField();
                            var errorMSg = error.getDefaultMessage();

                            errors.put(fieldName, errorMSg);
                        }
                );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
