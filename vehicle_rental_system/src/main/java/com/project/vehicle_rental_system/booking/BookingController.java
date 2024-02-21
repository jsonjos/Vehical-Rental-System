package com.project.vehicle_rental_system.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookvehicle")
    public String bookVehicle(@RequestBody BookingDto bookingDto) throws BookingException {
        return bookingService.vehicleBooking(bookingDto);
    }
    @PostMapping("/amounttransfer")
    public String amountTransaction(@RequestBody PaymentDto paymentDto)
    {
        return bookingService.bookingPayment(paymentDto);
    }

    @PostMapping("/returnvehicle")
    public String returnVehicle(@RequestBody ReturnDto returnDto)
    {
        return bookingService.returnVehicle(returnDto);
    }
}
