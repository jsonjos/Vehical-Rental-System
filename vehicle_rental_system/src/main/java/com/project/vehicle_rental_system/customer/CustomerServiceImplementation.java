package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.admin.exceptions.DeleteCustomerException;
import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.booking.BookingRepository;
import com.project.vehicle_rental_system.customer.Dto.DeactivateAccountDTO;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerServiceImplementation implements CustomerService {
    final
    CustomerRepository customerRepository;
    final BookingRepository bookingRepository;

    public CustomerServiceImplementation(CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }
    @Override
    public Customer loginCustomer(String customerEmail, String customerPassword) throws LoginException {

        Optional<Customer> optCustomer = customerRepository.findByCustomerEmail(customerEmail);
        if (optCustomer.isEmpty()) {
            throw new LoginException("User with email: " + customerEmail + " not found.Please provide the valid details!");
        }
        Customer validatingCustomer = optCustomer.get();
        if(!validatingCustomer.getAccountStatus()){
            throw new LoginException("Account has been deactivated");
        }
        if (validatingCustomer.getCustomerPassword().equals(customerPassword)) {
            return validatingCustomer;
        }
        throw new LoginException("Please provide valid password.");
    }

    @Override
    public Customer registerCustomer(CustomerDto newCustomer) throws RegisterException {
        Optional<Customer> optCustomer = customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if (optCustomer.isPresent()) {
            throw new RegisterException("User with Email " + newCustomer.getCustomerEmail() + " is already present");
        }
        if (!usernameValidator(newCustomer.getCustomerName())) {
            throw new RegisterException("Incorrect username format");
        }
        if (!passwordValidator(newCustomer.getCustomerPassword())) {
            throw new RegisterException("Incorrect password format");
        }
        if (!emailValidator(newCustomer.getCustomerEmail())) {
            throw new RegisterException("Incorrect email format");
        }
        Customer customer = new Customer();
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setCustomerEmail(newCustomer.getCustomerEmail().toLowerCase());
        customer.setCustomerPassword(newCustomer.getCustomerPassword());
        customer.setAccountStatus(true);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public List<Booking> viewBookings(Integer customerId) {
        List<Booking> bookingList = new ArrayList<>(bookingRepository.findAll());
        List<Booking> list=bookingList.stream().filter(booking->booking.getCustomer().getCustomerId().equals(customerId)).toList();
        return list;
    }

    @Override
    public Customer deactivateAccount(Integer customerId, DeactivateAccountDTO customerPassword) throws CustomerException {

        Optional<Customer> foundCustomer=customerRepository.findById(customerId);
        if(foundCustomer.isEmpty()){
            throw new CustomerException("Customer not found");
        }
        Customer updateCustomer=foundCustomer.get();
        if(updateCustomer.getCustomerPassword().equals(customerPassword.getCustomerPassword())){
            updateCustomer.setAccountStatus(false);
            return customerRepository.save(updateCustomer);
        }
        else {
            throw new CustomerException("Enter valid password");
        }
    }


    public boolean usernameValidator(String userName) {
        String regex = "^[a-zA-Z0-9]+[a-zA-Z0-9][_ -]?[a-zA-Z0-9]*[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    public boolean emailValidator(String email) {
        String regex = "[a-z0-9A-Z]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean passwordValidator(String password) {
        String regex = "^(?=.*\\d)" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
