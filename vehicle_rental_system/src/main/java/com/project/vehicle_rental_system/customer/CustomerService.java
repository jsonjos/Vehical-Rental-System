package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.admin.exceptions.DeleteCustomerException;
import com.project.vehicle_rental_system.bank.Account;
import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.customer.Dto.DeactivateAccountDTO;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;

import java.util.List;

public interface CustomerService {
    Customer loginCustomer(String customerEmail, String customerPassword) throws LoginException;

    Customer registerCustomer(CustomerDto customer) throws RegisterException;

    List<Booking> viewBookings(Integer customerId);

    Customer deactivateAccount(Integer customerId, DeactivateAccountDTO customerPassword) throws CustomerException;
}
