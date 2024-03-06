package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.booking.BookingRepository;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImplementation implements CustomerService{
    final
    CustomerRepository customerRepository;
    final BookingRepository bookingRepository;

    public CustomerServiceImplementation(CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public String loginCustomer(String customerEmail,String customerPassword) throws LoginException {

        Optional<Customer> optCustomer= customerRepository.findByCustomerEmail(customerEmail);
        if(optCustomer.isEmpty())
        {
            throw new LoginException("User with email: "+customerEmail+" not found.Please provide the valid details!");

        }
        Customer validatingCustomer= optCustomer.get();
        if (validatingCustomer.getCustomerPassword().equals(customerPassword)) {
            return "Login successful.";
        }
        throw new LoginException("Please provide valid password.");
    }

    @Override
    public String registerCustomer(CustomerDto newCustomer) throws RegisterException {
        Optional<Customer>optCustomer= customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());
        if(optCustomer.isPresent())
        {
            throw new RegisterException("User with Email "+ newCustomer.getCustomerEmail()+" is already present");
        }
        if(!usernameValidator(newCustomer.getCustomerName()))
        {
            throw new RegisterException("Incorrect username format");
        }
        if(!passwordValidator(newCustomer.getCustomerPassword()))
        {
            throw new RegisterException("Incorrect password format");
        }
        if(!emailValidator(newCustomer.getCustomerEmail()))
        {
            throw new RegisterException("Incorrect email format");
        }
        Customer customer=new Customer();
        customer.setCustomerId(newCustomer.getCustomerId());
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setCustomerEmail(newCustomer.getCustomerEmail());
        customer.setCustomerPassword(newCustomer.getCustomerPassword());
        customerRepository.save(customer);
        return "User Registered Successfully";
    }

    @Override
    public List<Booking> viewBookings(Integer customerId) {
        List<Booking> bookingList=new ArrayList<>(bookingRepository.findAll());
        return bookingList.stream().filter
                        (s -> s.getBookingId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteAccount(CustomerDto customer) {
        Integer customerId= customer.getCustomerId();
        customerRepository.deleteById(customerId);
        return "Account deleted";
    }

    public boolean usernameValidator(String userName)
    {
        String regex="^[a-zA-Z0-9]+[a-zA-Z0-9][_ -]?[a-zA-Z0-9]*[a-zA-Z0-9]+$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(userName);
        return matcher.matches();
    }

    public boolean emailValidator(String email)
    {
        String regex="[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }
    public boolean passwordValidator(String password)
    {
        String regex="^(?=.*\\d)"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }
}
