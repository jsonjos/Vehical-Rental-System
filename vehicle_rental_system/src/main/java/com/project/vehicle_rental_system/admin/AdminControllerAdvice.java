package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.admin.exceptions.AdminException;
import com.project.vehicle_rental_system.admin.exceptions.DeleteCustomerException;
import com.project.vehicle_rental_system.admin.exceptions.GetCustomerException;
import com.project.vehicle_rental_system.admin.exceptions.UpdateCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdminControllerAdvice {
    @ExceptionHandler(value = AdminException.class)
    public ResponseEntity<String> AdminHandlerException(AdminException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = DeleteCustomerException.class)
    public ResponseEntity<String> AdminHandlerException(DeleteCustomerException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = GetCustomerException.class)
    public ResponseEntity<String> AdminHandlerException(GetCustomerException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = UpdateCustomerException.class)
    public ResponseEntity<String> AdminHandlerException(UpdateCustomerException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
