package com.project.vehicle_rental_system.booking;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class BookingDto {

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    Integer bookingId;


    Integer vehicleId;
//
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    Integer paymentId;

}
