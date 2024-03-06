package com.project.vehicle_rental_system.booking;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class BookingDto {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer bookingId;

    //Integer noOfDays;

    Integer vehicleId;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer paymentId;

}
