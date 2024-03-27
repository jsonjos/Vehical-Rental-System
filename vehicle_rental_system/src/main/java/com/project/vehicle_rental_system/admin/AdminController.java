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

@RestController
@RequestMapping("home")
@CrossOrigin(origins = {"http://localhost:3000/","http://localhost:4200/"})
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("customer/add")
    public Customer addCustomer(@RequestBody CustomerDto customer) throws AddCustomerException {
        Customer savedCustomer = adminService.addCustomer(customer);
        return savedCustomer;
    }

    @PutMapping("customer/update")
    public Customer updateCustomer(@RequestBody CustomerDto customer) throws UpdateCustomerException {
       Customer updatedCustomer= adminService.updateCustomer(customer);
        return updatedCustomer;
    }

//    @GetMapping("customer/find")
//    public ResponseEntity<Customer> getCustomer(@Valid @RequestBody Integer customerId) throws GetCustomerException {
//        Optional<Customer> customerOpt = adminService.getCustomerById(customerId);
//        if (customerOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        Customer customer = customerOpt.get();
//        return ResponseEntity.ok(customer);
//    }

    @GetMapping("AllCustomerDetails")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @DeleteMapping("customer/delete/{id}")
    public String deleteCustomer(@Valid @PathVariable("id") Integer customerId) throws DeleteCustomerException {
        return adminService.deleteCustomer(customerId);
    }

    @PostMapping("admin/login")
    public Admin loginAdmin(@Valid @RequestBody AdminLoginDTO adminLoginDTO)throws AdminException{
        return adminService.loginAdmin(adminLoginDTO);
    }


}
