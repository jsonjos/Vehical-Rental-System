package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.admin.exceptions.DeleteCustomerException;
import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.customer.Dto.DeactivateAccountDTO;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
@RestController
@RequestMapping("home")
public class CustomerController {
    final
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("login/customer")
    public Customer loginCustomer(@Valid @RequestBody  CustomerLoginDto customer) throws LoginException {

        return customerService.loginCustomer(customer.getCustomerEmail(), customer.getCustomerPassword());
    }

    @PostMapping("register/customer")
    public Customer registerCustomer(@Valid @RequestBody  CustomerDto customer) throws RegisterException {
        return customerService.registerCustomer(customer);
    }
    @GetMapping("/bookingList/{customerId}")
    public List<Booking> bookingList(@Valid @PathVariable Integer customerId){
        return customerService.viewBookings(customerId);
    }

    @PatchMapping("/deactivate/{customerId}")
    public Customer deactivateAccount(@PathVariable Integer customerId, @Valid @RequestBody DeactivateAccountDTO customerPassword) throws CustomerException {
        return customerService.deactivateAccount(customerId, customerPassword);
    }
}
