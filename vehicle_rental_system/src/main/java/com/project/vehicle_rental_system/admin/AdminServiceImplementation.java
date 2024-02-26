package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerException;
import com.project.vehicle_rental_system.customer.CustomerRepository;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import com.project.vehicle_rental_system.vehicle.VehicleException;
import com.project.vehicle_rental_system.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Admin loginAdmin(Admin admin) throws AdminException {
        Optional<Admin> adminopt= adminRepository.findById(admin.getAdminId());
        if(adminopt.isEmpty())
        {
            throw new AdminException("Admin not found....Please provide the valid details!!!");
        }
        Admin validatingAdmin= adminRepository.getById(admin.getAdminId());
        try {
            if (validatingAdmin.getAdminName().equals(admin.getAdminName())) {
                try {
                    if (validatingAdmin.getAdminPassword().equals(admin.getAdminPassword())) {
                        return admin;
                    }
                    throw new RuntimeException();
                }
                catch(Exception e)
                {
                    throw new AdminException("Please provide the valid password....");
                }

            }
            throw new RuntimeException();
        }
        catch(Exception e)
        {
            throw  new AdminException("Please provide the valid username.....");
        }

    }

    @Override
    public Customer addCustomer(Customer newCustomer) throws CustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if(customerOpt.isPresent())
            throw new CustomerException("Email already registered, please re try."+newCustomer.getCustomerEmail());


        return this.customerRepository.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(Customer newCustomer) throws CustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if(customerOpt.isEmpty())
            throw new CustomerException("Email not found!"+newCustomer.getCustomerEmail());
        System.out.println("commit check");

        return this.customerRepository.save(newCustomer);
    }

    @Override
    public Customer getCustomerbyId(Integer id) throws CustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findById(id);
        if(customerOpt.isEmpty())
            throw new CustomerException("Customer id not found!!"+id);
        return this.customerRepository.findById(id).get();
    }

    @Override
    public Customer deleteCustomer(Integer id) throws CustomerException {
        Optional<Customer> customerOpt = this.customerRepository.findById(id);
        if(customerOpt.isEmpty())
            throw new CustomerException("Customer id not found!!"+id);
        this.customerRepository.deleteById(id);
        return customerOpt.get();
    }


    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

}
