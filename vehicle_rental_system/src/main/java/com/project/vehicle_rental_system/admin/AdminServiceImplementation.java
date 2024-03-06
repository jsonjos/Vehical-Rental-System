package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerDto;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
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
    public Customer addCustomer(CustomerDto newCustomer) throws CustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if (customerOpt.isPresent())
            throw new CustomerException("Email already registered, please re-try." + newCustomer.getCustomerEmail());
        Customer customer=new Customer();
        customer.setCustomerId(newCustomer.getCustomerId());
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setCustomerEmail(newCustomer.getCustomerEmail());
        customer.setCustomerPassword(newCustomer.getCustomerPassword());
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(CustomerDto newCustomer) throws CustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if (customerOpt.isEmpty())
            throw new CustomerException("Email not found! " + newCustomer.getCustomerEmail());
        Customer customer=customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail()).get();
        customer.setCustomerId(newCustomer.getCustomerId());
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setCustomerEmail(newCustomer.getCustomerEmail());
        customer.setCustomerPassword(newCustomer.getCustomerPassword());
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Integer id) throws CustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findById(id);
        if (customerOpt.isEmpty())
            throw new CustomerException("Customer with id: " + id + " not found!");
        return this.customerRepository.findById(id).get();
    }

    @Override
    public Customer deleteCustomer(Integer id) throws CustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findById(id);
        if (customerOpt.isEmpty())
            throw new CustomerException("Customer with id: " + id + " not found!");
        this.customerRepository.deleteById(id);
        return customerOpt.get();
    }


    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

}
