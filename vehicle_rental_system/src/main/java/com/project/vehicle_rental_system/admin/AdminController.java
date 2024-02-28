package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.admin.exceptions.*;
import com.project.vehicle_rental_system.customer.Customer;

import com.project.vehicle_rental_system.customer.CustomerDto;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("login/admin")
    public String loginAdmin(@RequestBody Admin admin) throws AdminException {
        return adminService.loginAdmin(admin.getAdminId(), admin.getAdminName(),admin.getAdminPassword());
    }
    @PostMapping("customer/add")
    public Customer addCustomer(@RequestBody Customer customer) throws AddCustomerException {
        return adminService.addCustomer(customer);
    }

    @PutMapping("customer/update")
    public Customer updateCustomer(@RequestBody Customer customer) throws UpdateCustomerException {
        return adminService.updateCustomer(customer);
    }

    @GetMapping("{Id}")
    public Customer getCustomer(@PathVariable("Id") Integer customerId) throws GetCustomerException {
        return adminService.getCustomerById(customerId);
    }

    @GetMapping("AllCustomerDetails")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @DeleteMapping("{id}")
    public Customer deleteCustomer(@PathVariable("id") Integer customerId) throws DeleteCustomerException {
        return adminService.deleteCustomer(customerId);
    }


}
