package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;

import java.util.List;

public interface AdminService {
    Admin loginAdmin(Admin admin) throws AdminException;

    Customer addCustomer(Customer customer) throws LoginException;

    Customer updateCustomer(Customer customer) throws LoginException;

    Customer getCustomerById(Integer id) throws LoginException;

    Customer deleteCustomer(Integer id) throws LoginException;

    List<Customer> getAllCustomers();

}
