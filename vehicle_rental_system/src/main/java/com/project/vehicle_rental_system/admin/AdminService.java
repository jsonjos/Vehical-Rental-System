package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.admin.exceptions.*;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerDto;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;

import java.util.List;

public interface AdminService {
    String loginAdmin(Integer adminId,String adminName, String adminPassword) throws AdminException;

    Customer addCustomer(Customer customer) throws AddCustomerException;

    Customer updateCustomer(Customer customer) throws UpdateCustomerException;

    Customer getCustomerById(Integer id) throws GetCustomerException;

    Customer deleteCustomer(Integer id) throws DeleteCustomerException;

    List<Customer> getAllCustomers();

}
