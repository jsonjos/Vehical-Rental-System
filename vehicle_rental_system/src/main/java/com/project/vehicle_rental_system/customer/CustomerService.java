package com.project.vehicle_rental_system.customer;

public interface CustomerService {
    public String loginCustomer(Integer customerId,String customerName,String customerPassword) throws CustomerException;

    public String registerCustomer(CustomerDto customer) throws CustomerException;
}
