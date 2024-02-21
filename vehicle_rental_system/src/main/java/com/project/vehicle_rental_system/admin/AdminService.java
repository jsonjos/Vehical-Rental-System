package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerException;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import com.project.vehicle_rental_system.vehicle.VehicleException;

import java.util.List;

public interface AdminService {
    public Admin loginAdmin(Admin admin) throws AdminException;

    public Customer addCustomer(Customer cutomer) throws CustomerException;
    public Customer updateCustomer(Customer cutomer) throws CustomerException;
    public Customer getCustomerbyId(Integer id) throws CustomerException;
    public Customer deleteCustomer(Integer id) throws CustomerException;
    List<Customer> getAllCustomers();

}
