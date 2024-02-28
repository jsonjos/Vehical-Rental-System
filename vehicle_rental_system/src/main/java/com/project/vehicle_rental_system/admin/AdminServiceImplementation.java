package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.admin.exceptions.*;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminServiceImplementation implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;
    // Regular expressions for email and password validation
    private static final String EMAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.\\w+$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    @Override
    public String loginAdmin(Integer adminId,String adminEmail, String adminPassword) throws AdminException {

        // Validate adminName format
        validateEmail(adminEmail);
        // Validate adminPassword format
        validatePassword(adminPassword);

        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isEmpty()) {
            throw new AdminException("Admin not found.Please provide the valid details!");
        }
        Admin validatingAdmin = adminRepository.getById(adminId);
        try {
            if (validatingAdmin.getAdminName().equals(adminEmail)) {
                try {
                    if (validatingAdmin.getAdminPassword().equals(adminPassword)) {
                        return "Login Success";
                    }
                    throw new RuntimeException();
                } catch (Exception e) {
                    throw new AdminException("Please provide the valid password.");
                }

            }
            throw new RuntimeException();
        } catch (Exception e) {
            throw new AdminException("Please provide the valid username.");
        }

    }

    @Override
    public Customer addCustomer(Customer newCustomer) throws AddCustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if (customerOpt.isPresent())
            throw new AddCustomerException("Email already registered, please re-try." + newCustomer.getCustomerEmail());

        return this.customerRepository.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(Customer newCustomer) throws UpdateCustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if (customerOpt.isEmpty())
            throw new UpdateCustomerException("Email not found! " + newCustomer.getCustomerEmail());

        return this.customerRepository.save(newCustomer);
    }

    @Override
    public Customer getCustomerById(Integer id) throws GetCustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findById(id);
        if (customerOpt.isEmpty())
            throw new GetCustomerException("Customer with id: " + id + " not found!");
        return this.customerRepository.findById(id).get();
    }

    @Override
    public Customer deleteCustomer(Integer id) throws DeleteCustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findById(id);
        if (customerOpt.isEmpty())
            throw new DeleteCustomerException("Customer with id: " + id + " not found!");
        this.customerRepository.deleteById(id);
        return customerOpt.get();
    }


    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    // Validator method for email format
    private void validateEmail(String email) throws AdminException {
        if (!validateInput(email, EMAIL_PATTERN)) {
            throw new AdminException("Invalid email format. Please provide a valid email address.");
        }
    }

    // Validator method for password format
    private void validatePassword(String password) throws AdminException {
        if (!validateInput(password, PASSWORD_PATTERN)) {
            throw new AdminException("Invalid password format. Please provide a password with at least 8 characters including letters and numbers.");
        }
    }

    // Method to validate input data against a given pattern using regular expressions
    private boolean validateInput(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        return matcher.matches();
    }

}
