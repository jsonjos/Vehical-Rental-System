package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Admin loginAdmin(Admin admin) throws AdminException {
        Optional<Admin> adminOptional = adminRepository.findById(admin.getAdminId());
        if (adminOptional.isEmpty()) {
            throw new AdminException("Admin not found.Please provide the valid details!");
        }
        Admin validatingAdmin = adminRepository.getById(admin.getAdminId());
        try {
            if (validatingAdmin.getAdminName().equals(admin.getAdminName())) {
                try {
                    if (validatingAdmin.getAdminPassword().equals(admin.getAdminPassword())) {
                        return admin;
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
    public Customer addCustomer(Customer newCustomer) throws LoginException {
        Optional<Customer> customerOpt = this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if (customerOpt.isPresent())
            throw new LoginException("Email already registered, please re-try." + newCustomer.getCustomerEmail());

        return this.customerRepository.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(Customer newCustomer) throws LoginException {
        Optional<Customer> customerOpt = this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if (customerOpt.isEmpty())
            throw new LoginException("Email not found! " + newCustomer.getCustomerEmail());

        return this.customerRepository.save(newCustomer);
    }

    @Override
    public Customer getCustomerById(Integer id) throws LoginException {
        Optional<Customer> customerOpt = this.customerRepository.findById(id);
        if (customerOpt.isEmpty())
            throw new LoginException("Customer with id: " + id + " not found!");
        return this.customerRepository.findById(id).get();
    }

    @Override
    public Customer deleteCustomer(Integer id) throws LoginException {
        Optional<Customer> customerOpt = this.customerRepository.findById(id);
        if (customerOpt.isEmpty())
            throw new LoginException("Customer with id: " + id + " not found!");
        this.customerRepository.deleteById(id);
        return customerOpt.get();
    }


    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

}
