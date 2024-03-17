package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.booking.exceptions.*;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;

import java.util.List;

public interface BookingService {

    String vehicleBooking(BookingDto bookingDto) throws NegativeNumberException, VehicleNotFoundException, CustomerException;
    String bookingPayment(PaymentDto paymentDto) throws  BalanceException, CustomerBankAccountException;
    String returnVehicle(ReturnDto returnDto) throws ReturnLocationException, NoActiveException;

}
