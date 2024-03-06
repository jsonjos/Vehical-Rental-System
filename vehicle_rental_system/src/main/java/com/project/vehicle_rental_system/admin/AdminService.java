package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.admin.exceptions.AdminException;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerDto;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;

import java.util.List;

public interface AdminService {
    Admin loginAdmin(Admin admin) throws AdminException;

    Customer addCustomer(CustomerDto customer) throws CustomerException;

    Customer updateCustomer(CustomerDto customer) throws CustomerException;

    Customer getCustomerById(Integer id) throws CustomerException;

    Customer deleteCustomer(Integer id) throws CustomerException;

    List<Customer> getAllCustomers();

}
