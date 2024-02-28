package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.booking.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookingControllerAdvice {
    @ExceptionHandler(value = BalanceException.class)
    public ResponseEntity<String> bookingHandlerException(BalanceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CustomerBankAccountException.class)
    public ResponseEntity<String> bookingHandlerException(CustomerBankAccountException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NegativeNumberException.class)
    public ResponseEntity<String> bookingHandlerException(NegativeNumberException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ReturnLocationException.class)
    public ResponseEntity<String> bookingHandlerException(ReturnLocationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = VehicleNotFoundException.class)
    public ResponseEntity<String> bookingHandlerException(VehicleNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = DaysMismatchException.class)
    public ResponseEntity<String> bookingHandlerException(DaysMismatchException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
