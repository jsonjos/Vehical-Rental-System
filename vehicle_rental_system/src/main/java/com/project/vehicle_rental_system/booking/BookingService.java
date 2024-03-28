package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.booking.exceptions.*;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import com.project.vehicle_rental_system.payment.Payment;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;

import java.util.List;

public interface BookingService {

    Booking vehicleBooking(Integer customerId,BookingDto bookingDto) throws NegativeNumberException, VehicleNotFoundException, CustomerException;
    Payment bookingPayment(Integer bookingId,PaymentDto paymentDto) throws  BalanceException, CustomerBankAccountException, NoBookingException;
    Vehicle returnVehicle(ReturnDto returnDto) throws ReturnLocationException, NoActiveException;

}
