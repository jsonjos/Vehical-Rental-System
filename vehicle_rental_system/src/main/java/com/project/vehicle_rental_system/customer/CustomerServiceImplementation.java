package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;
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
    public String loginCustomer(Integer customerId,String customerName,String customerPassword) throws LoginException {
        Optional<Customer> optCustomer= customerRepository.findById(customerId);
        if(optCustomer.isEmpty())
        {
            throw new LoginException("User with Id: "+customerId+"not found.Please provide the valid details!");
        }
        Customer validatingCustomer= customerRepository.getById(customerId);
            if (validatingCustomer.getCustomerName().equals(customerName)) {
                    if (validatingCustomer.getCustomerPassword().equals(customerPassword)) {
                        return "Login successful.";
                    }

                    throw new LoginException("Please provide valid password.");
            }
            throw  new LoginException("Please provide valid username.");
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
//        if(!mobileValidator(newCustomer.getUserName()))
//        {
//            throw new UserException("Incorrect username format.... ");
//        }
        Customer customer=new Customer();
        customer.setCustomerId(newCustomer.getCustomerId());
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setCustomerEmail(newCustomer.getCustomerEmail());
        customer.setCustomerPassword(newCustomer.getCustomerPassword());
        customerRepository.save(customer);
        return "User Registered Successfully";
    }

    public boolean usernameValidator(String userName)
    {
        String regex="^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(userName);
        return matcher.matches();
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
