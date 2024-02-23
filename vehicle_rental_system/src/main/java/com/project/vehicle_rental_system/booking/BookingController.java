package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.booking.exceptions.*;
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

    @PostMapping("/bookVehicle")
    public String bookVehicle(@RequestBody BookingDto bookingDto) throws VehicleNotFoundException, NegativeNumberException {
        return bookingService.vehicleBooking(bookingDto);
    }
    @PostMapping("/amountTransfer")
    public String amountTransaction(@RequestBody PaymentDto paymentDto) throws CustomerBankAccountException, BalanceException {
        return bookingService.bookingPayment(paymentDto);
    }

    @PostMapping("/returnVehicle")
    public String returnVehicle(@RequestBody ReturnDto returnDto) throws ReturnLocationException {
        return bookingService.returnVehicle(returnDto);
    }
}
