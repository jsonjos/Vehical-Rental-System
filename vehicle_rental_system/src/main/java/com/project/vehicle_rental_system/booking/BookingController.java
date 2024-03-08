package com.project.vehicle_rental_system.booking;
import com.project.vehicle_rental_system.booking.exceptions.*;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookVehicle")
    public String bookVehicle(@Valid @RequestBody BookingDto bookingDto) throws VehicleNotFoundException, NegativeNumberException {
        return bookingService.vehicleBooking(bookingDto);
    }
    @PostMapping("/amountTransfer")
    public String amountTransaction(@Valid @RequestBody PaymentDto paymentDto) throws CustomerBankAccountException, BalanceException {
        return bookingService.bookingPayment(paymentDto);
    }

    @PostMapping("/returnVehicle")
    public String returnVehicle(@Valid @RequestBody ReturnDto returnDto) throws ReturnLocationException, NoActiveException {
        return bookingService.returnVehicle(returnDto);
    }
}
