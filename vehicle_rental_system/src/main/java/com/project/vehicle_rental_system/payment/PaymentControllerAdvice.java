package com.project.vehicle_rental_system.payment;
import com.project.vehicle_rental_system.payment.exceptions.RentPaymentException;
import com.project.vehicle_rental_system.payment.exceptions.ReturnPaymentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


    @RestControllerAdvice
    public class PaymentControllerAdvice {
        @ExceptionHandler(value = RentPaymentException.class)
        public ResponseEntity<String> paymentHandlerException(RentPaymentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(value = ReturnPaymentException.class)
        public ResponseEntity<String> paymentHandlerException(ReturnPaymentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
