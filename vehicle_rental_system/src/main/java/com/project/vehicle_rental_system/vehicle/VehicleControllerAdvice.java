package com.project.vehicle_rental_system.vehicle;

import com.project.vehicle_rental_system.vehicle.exceptions.DeleteVehicleException;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import com.project.vehicle_rental_system.vehicle.exceptions.UpdateVehicleException;
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
public class VehicleControllerAdvice {
    @ExceptionHandler(value= UpdateVehicleException.class)
    public ResponseEntity<String> vehicleHandlerException(UpdateVehicleException e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value= DeleteVehicleException.class)
    public ResponseEntity<String> vehicleHandlerException(DeleteVehicleException e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value= NoActiveException.class)
    public ResponseEntity<String> vehicleHandlerException(NoActiveException e)
    {
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
