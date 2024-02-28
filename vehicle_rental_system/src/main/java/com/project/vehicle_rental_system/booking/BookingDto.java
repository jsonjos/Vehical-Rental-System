package com.project.vehicle_rental_system.booking;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingDto {

    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer bookingId;

    Integer noOfDays;

    Integer vehicleId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer paymentId;

}
