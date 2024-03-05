package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("home")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("login/customer")
    public String loginCustomer(@RequestBody CustomerDto customer) throws LoginException {
        return customerService.loginCustomer(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerPassword());
    }

    @PostMapping("register/customer")
    public String registerCustomer(@RequestBody CustomerDto customer) throws RegisterException {
        return customerService.registerCustomer(customer);
    }
}
