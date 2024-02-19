package com.project.vehicle_rental_system.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImplementation implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;
}
