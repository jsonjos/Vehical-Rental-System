package com.project.vehicle_rental_system.payment;

import com.project.vehicle_rental_system.admin.Admin;
import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.customer.Customer;

public interface PaymentService {
    boolean rentPayment(Customer customer, Booking booking, Admin admin) throws PaymentException;

    boolean returnPayment(Customer customer, Booking booking, Admin admin) throws PaymentException;
}
