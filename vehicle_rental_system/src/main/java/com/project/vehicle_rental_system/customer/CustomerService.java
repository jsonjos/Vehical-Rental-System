package com.project.vehicle_rental_system.customer;

public interface CustomerService {
    Customer loginCustomer(CustomerLoginDTO customerLoginDTO) throws CustomerException;

    String registerCustomer(Customer customer) throws CustomerException;
}
