package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerException;

import java.util.List;

public interface AdminService {
    Admin loginAdmin(Admin admin) throws AdminException;

    Customer addCustomer(Customer customer) throws CustomerException;

    Customer updateCustomer(Customer customer) throws CustomerException;

    Customer getCustomerById(Integer id) throws CustomerException;

    Customer deleteCustomer(Integer id) throws CustomerException;

    List<Customer> getAllCustomers();

}
