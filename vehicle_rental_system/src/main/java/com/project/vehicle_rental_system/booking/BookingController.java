package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.bank.exceptions.AccountException;
import com.project.vehicle_rental_system.booking.exceptions.*;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import com.project.vehicle_rental_system.payment.Payment;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("home")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookVehicle/{customerId}")
    public Booking bookVehicle(@PathVariable Integer customerId, @Valid @RequestBody BookingDto bookingDto) throws VehicleNotFoundException, NegativeNumberException, CustomerException {
        return bookingService.vehicleBooking(customerId,bookingDto);
    }
    @PostMapping("/amountTransfer")
    public Payment amountTransaction(@Valid @RequestBody PaymentDto paymentDto) throws CustomerBankAccountException, BalanceException, NoBookingException {
        return bookingService.bookingPayment(paymentDto);
    }

    @PostMapping("/returnVehicle")
    public Vehicle returnVehicle(@Valid @RequestBody ReturnDto returnDto) throws ReturnLocationException, NoActiveException {
        return bookingService.returnVehicle(returnDto);
    }

}
