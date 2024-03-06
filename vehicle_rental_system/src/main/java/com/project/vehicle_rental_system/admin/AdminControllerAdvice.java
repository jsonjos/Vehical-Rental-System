package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.admin.exceptions.AdminException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminControllerAdvice {
    @ExceptionHandler(value = AdminException.class)
    public ResponseEntity<String> AdminHandlerException(AdminException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
