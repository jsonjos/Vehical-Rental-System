package com.project.vehicle_rental_system.customer;

public interface CustomerService {
    String loginCustomer(Integer customerId, String customerName, String customerPassword) throws CustomerException;

    String registerCustomer(Customer customer) throws CustomerException;
}
