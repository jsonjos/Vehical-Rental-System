package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("home")
public class CustomerController {
    final
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("login/customer")
    public String loginCustomer(@RequestBody  CustomerLoginDto customer) throws LoginException {

        return customerService.loginCustomer(customer.getCustomerEmail(), customer.getCustomerPassword());
    }

    @PostMapping("register/customer")
    public String registerCustomer(@RequestBody  CustomerDto customer) throws RegisterException {
        return customerService.registerCustomer(customer);
    }
}
