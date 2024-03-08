package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;

import java.util.List;

public interface CustomerService {
    String loginCustomer(String customerEmail, String customerPassword) throws LoginException;

    String registerCustomer(CustomerDto customer) throws RegisterException;

    List<Booking> viewBookings(Integer customerId);

    String deleteAccount(DeleteCustomerDto customer);
}
