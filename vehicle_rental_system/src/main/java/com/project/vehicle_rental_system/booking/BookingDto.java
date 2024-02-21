package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.bank.Account;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDto {

    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer bookingId;

    Integer vehicleId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer paymentId;

}