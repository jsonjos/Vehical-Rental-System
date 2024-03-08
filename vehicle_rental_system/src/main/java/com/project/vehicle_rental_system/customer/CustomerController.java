package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home")
public class CustomerController {
    final
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("login/customer")
    public String loginCustomer(@Valid @RequestBody  CustomerLoginDto customer) throws LoginException {

        return customerService.loginCustomer(customer.getCustomerEmail(), customer.getCustomerPassword());
    }

    @PostMapping("register/customer")
    public String registerCustomer(@Valid @RequestBody  CustomerDto customer) throws RegisterException {
        return customerService.registerCustomer(customer);
    }
    @GetMapping("/bookingList")
    public List<Booking> bookingList(@Valid @RequestBody Integer customerId){
        return customerService.viewBookings(customerId);
    }

    @DeleteMapping("delete/customer")
    public String deleteMapping(@Valid @RequestBody DeleteCustomerDto customer){
        return this.customerService.deleteAccount(customer);
    }
}
