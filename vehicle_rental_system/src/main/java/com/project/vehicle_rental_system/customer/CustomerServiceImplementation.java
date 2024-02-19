package com.project.vehicle_rental_system.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerServiceImplementation implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer loginCustomer(Customer customer) throws CustomerException {
        Optional<Customer> optCustomer= customerRepository.findById(customer.getCustomerId());
        if(optCustomer.isEmpty())
        {
            throw new CustomerException("User not found....Please provide the valid details!!!");
        }
        Customer validatingCustomer= customerRepository.getById(customer.getCustomerId());
        try {
            if (validatingCustomer.getCustomerName().equals(customer.getCustomerName())) {
                try {
                    if (validatingCustomer.getCustomerPassword().equals(customer.getCustomerPassword())) {
                        return customer;
                    }
                    throw new RuntimeException();
                }
                catch(Exception e)
                {
                    throw new CustomerException("Please provide the valid password....");
                }

            }
            throw new RuntimeException();
        }
        catch(Exception e)
        {
            throw  new CustomerException("Please provide the valid username.....");
        }

    }

    @Override
    public String registerCustomer(Customer newCustomer) throws CustomerException {
        Optional<Customer>optCustomer= customerRepository.findById(newCustomer.getCustomerId());
        if(optCustomer.isPresent())
        {
            throw new CustomerException("User is already present......");
        }
        if(!usernameValidator(newCustomer.getCustomerName()))
        {
            throw new CustomerException("Incorrect username format.... ");
        }
        if(!passwordValidator(newCustomer.getCustomerPassword()))
        {
            throw new CustomerException("Incorrect password format.... ");
        }
        if(!emailValidator(newCustomer.getCustomerEmail()))
        {
            throw new CustomerException("Incorrect email format.... ");
        }
//        if(!mobileValidator(newCustomer.getUserName()))
//        {
//            throw new UserException("Incorrect username format.... ");
//        }
        customerRepository.save(newCustomer);
        return "User Registered Successfully";
    }

    public boolean usernameValidator(String userName)
    {
        String regex="^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(userName);
        if(matcher.matches())
        {
            return true;
        }
        return false;
    }

    public boolean emailValidator(String email)
    {
        String regex="[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(email);
        if(matcher.matches())
        {
            return true;
        }
        return false;
    }
    public boolean passwordValidator(String password)
    {
        String regex="^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(password);
        if(matcher.matches())
        {
            return true;
        }
        return false;
    }
}
