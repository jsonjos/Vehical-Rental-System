package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;

public interface CustomerService {
    String loginCustomer(String customerEmail, String customerPassword) throws LoginException;

    String registerCustomer(CustomerDto customer) throws RegisterException;
}
