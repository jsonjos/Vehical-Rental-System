package com.project.vehicle_rental_system.customer;

public interface CustomerService {
    public Customer loginCustomer(Customer customer) throws CustomerException;

    public String registerCustomer(Customer customer) throws CustomerException;
}
