package com.project.vehicle_rental_system.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("home")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("login/customer")
    public Customer loginCustomer(@RequestBody CustomerLoginDTO customer) throws CustomerException {
        return customerService.loginCustomer(customer);
    }

    @PostMapping("register/customer")
    public String registerCustomer(@RequestBody Customer customer) throws CustomerException {
        return customerService.registerCustomer(customer);
    }
}
