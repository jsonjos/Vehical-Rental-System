package com.project.vehicle_rental_system;

import com.project.vehicle_rental_system.admin.AdminService;
import com.project.vehicle_rental_system.admin.exceptions.AddCustomerException;
import com.project.vehicle_rental_system.admin.exceptions.DeleteCustomerException;
import com.project.vehicle_rental_system.admin.exceptions.GetCustomerException;
import com.project.vehicle_rental_system.admin.exceptions.UpdateCustomerException;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerDto;
import com.project.vehicle_rental_system.customer.CustomerRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class AdminTests {
    private static CustomerDto customerDto,customerDto2;

    @Autowired
    private AdminService adminService;

    @Autowired
    private static CustomerRepository customerRepository;

    @BeforeAll
    static void setup(){
        // Initialize customer objects if needed
    }

    @AfterAll
    static void removeSetup(){
        if (customerRepository != null) {
            customerRepository.deleteAll();
        } else {
            System.out.println("CustomerRepository is null. Unable to delete all customers.");
        }
    }


    @Test
    void testAddCustomer(){
        customerDto = new CustomerDto("Name","name@gmail.com","Password123");
        try {
            Assertions.assertNotNull(adminService.addCustomer(customerDto));
        } catch (AddCustomerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateCustomer(){
        customerDto = new CustomerDto("NewName","name@gmail.com","Password123");
        try {
            Assertions.assertNotNull(adminService.updateCustomer(customerDto));
        } catch (UpdateCustomerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetById(){
        customerDto = new CustomerDto("Name3","name3@gmail.com","Password123");
        try {
            Customer addedCustomer = adminService.addCustomer(customerDto);
            Integer customerId = addedCustomer.getCustomerId();
            if(customerId != null) {
                Optional<Customer> getCustomer = adminService.getCustomerById(customerId);
                Assertions.assertEquals(customerDto.getCustomerName(), getCustomer.get().getCustomerName());
                Assertions.assertEquals(customerDto.getCustomerEmail(), getCustomer.get().getCustomerEmail());
                Assertions.assertEquals(customerDto.getCustomerPassword(), getCustomer.get().getCustomerPassword());
            }
        } catch (AddCustomerException | GetCustomerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteCustomer(){
        customerDto = new CustomerDto("DeleteName","deletename@gmail.com","Password123");
        try {
            Customer addedCustomer = adminService.addCustomer(customerDto);
            Integer customerId = addedCustomer.getCustomerId();
            if(customerId != null) {
                String deleteMessage = adminService.deleteCustomer(customerId);
                Assertions.assertEquals("Customer deleted successfully", deleteMessage);
            }
        } catch (AddCustomerException | DeleteCustomerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAllCustomers(){
        customerDto2 = new CustomerDto("Name2","name2@gmail.com","Pass@456");
        try {
            adminService.addCustomer(customerDto2);
            List<Customer> customerList = adminService.getAllCustomers();
            System.out.println(customerList);
            Assertions.assertEquals(3, customerList.size());
        } catch (AddCustomerException e) {
            e.printStackTrace();
        }
    }
}
