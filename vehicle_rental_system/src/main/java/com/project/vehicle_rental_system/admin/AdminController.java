package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerDto;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("customer/add")
    public Customer addCustomer(@RequestBody CustomerDto customer) throws CustomerException {
        return adminService.addCustomer(customer);
    }

    @PutMapping("customer/update")
    public Customer updateCustomer(@RequestBody CustomerDto customer) throws CustomerException {
        return adminService.updateCustomer(customer);
    }

    @GetMapping("{Id}")
    public Customer getCustomer(@PathVariable("Id") Integer customerId) throws CustomerException {
        return adminService.getCustomerById(customerId);
    }

    @GetMapping("AllCustomerDetails")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @DeleteMapping("{id}")
    public Customer deleteCustomer(@PathVariable("id") Integer customerId) throws CustomerException {
        return adminService.deleteCustomer(customerId);
    }


}
