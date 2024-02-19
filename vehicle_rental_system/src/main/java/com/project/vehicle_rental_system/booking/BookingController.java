package com.project.vehicle_rental_system.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class BookingController {
    @Autowired
    private BookingService bookingService;
}
