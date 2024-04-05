package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.admin.exceptions.*;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
@RestController
@RequestMapping("home")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("customer/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto customer) throws AddCustomerException {
        Customer savedCustomer = adminService.addCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.ACCEPTED);
    }

    @PutMapping("customer/update")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customer) throws UpdateCustomerException {
        String response= adminService.updateCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("customer/{customerId}")
    public Customer getCustomer(@Valid @PathVariable("customerId") Integer customerId) throws GetCustomerException {
        return adminService.getCustomerById(customerId);
    }

    @GetMapping("AllCustomerDetails")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @DeleteMapping("customer/delete/{customerId}")
public String deleteCustomer(@PathVariable Integer customerId) throws DeleteCustomerException {
    return adminService.deleteCustomer(customerId);
}


    @PostMapping("admin/login")
    public Admin loginAdmin(@Valid @RequestBody AdminLoginDTO adminLoginDTO)throws AdminException{
        return adminService.loginAdmin(adminLoginDTO);
    }



}
