package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.booking.exceptions.*;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;

public interface BookingService {

    String vehicleBooking(BookingDto bookingDto) throws NegativeNumberException, VehicleNotFoundException;
    String bookingPayment(PaymentDto paymentDto) throws  BalanceException, CustomerBankAccountException;
    String returnVehicle(ReturnDto returnDto) throws ReturnLocationException, NoActiveException;
}
