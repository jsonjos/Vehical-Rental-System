package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.admin.exceptions.*;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerDto;
import com.project.vehicle_rental_system.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;

    public AdminServiceImplementation(AdminRepository adminRepository, CustomerRepository customerRepository) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public Admin loginAdmin(AdminLoginDTO adminLoginDTO) throws AdminException {
        Optional<Admin> adminOptional = adminRepository.findByAdminEmail(adminLoginDTO.getAdminEmail());
        if (adminOptional.isEmpty()) {
            throw new AdminException("Admin not found.Please provide the valid details!");
        }
        Admin validatingAdmin = adminOptional.get();
        if (!validatingAdmin.getAdminEmail().equals(adminLoginDTO.getAdminEmail())) {
            throw new AdminException("Invalid username");
        }
        if (!validatingAdmin.getAdminPassword().equals(adminLoginDTO.getAdminPassword())) {
            throw new AdminException("Invalid password");
        } else {
            Admin admin = new Admin();
            admin.setAdminEmail(adminLoginDTO.getAdminEmail());
            admin.setAdminPassword(adminLoginDTO.getAdminPassword());
            return admin;
        }
    }

    @Override
    public Customer addCustomer(CustomerDto newCustomer) throws AddCustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if (customerOpt.isPresent())
            throw new AddCustomerException("Email already registered, please re-try." + newCustomer.getCustomerEmail());
        Customer customer = new Customer();
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setCustomerEmail(newCustomer.getCustomerEmail());
        customer.setCustomerPassword(newCustomer.getCustomerPassword());
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(CustomerDto newCustomer) throws UpdateCustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if (customerOpt.isEmpty())
            throw new UpdateCustomerException("Email not found! " + newCustomer.getCustomerEmail());
        Customer customer = customerOpt.get();
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setCustomerEmail(newCustomer.getCustomerEmail());
        customer.setCustomerPassword(newCustomer.getCustomerPassword());
        this.customerRepository.save(customer);
        return customer;
    }

//    @Override
//    public Optional<Customer> getCustomerById(Integer id) throws GetCustomerException {
//        Optional<Customer> customerOpt = this.customerRepository.findByCustomerId(id);
//        if (customerOpt.isEmpty())
//            throw new GetCustomerException("Customer with id: " + id + " not found!");
//        //Customer customer = customerOpt.get();
//        return this.customerRepository.findByCustomerId(id);
//    }

    @Override
    public String deleteCustomer(Integer id) throws DeleteCustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findById(id);
        if (customerOpt.isEmpty())
            throw new DeleteCustomerException("Customer with id: " + id + " not found!");
        Customer customer = customerOpt.get();
        this.customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }


    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

}
