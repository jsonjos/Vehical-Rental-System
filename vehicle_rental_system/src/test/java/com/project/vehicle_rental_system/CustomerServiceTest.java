//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.project.vehicle_rental_system;

import com.project.vehicle_rental_system.customer.CustomerDto;
import com.project.vehicle_rental_system.customer.CustomerService;
import com.project.vehicle_rental_system.customer.exceptions.LoginException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    CustomerServiceTest() {
    }


    @DisplayName("Positive Customer account creation")
    @Test
    @Order(1)
    void createAccountTest() {
        try {
            Assertions.assertNotNull(customerService.registerCustomer(new CustomerDto("Nishanth", "nishanth@gmail.com", "Nish@123")));
        } catch (RegisterException var2) {
            throw new RuntimeException(var2);
        }
    }

    @Order(2)
    @DisplayName("Registering a duplicate user")
    @Test
    void duplicateRegisterUser() throws RegisterException {
//        this.customerService.registerCustomer(new CustomerDto("Jason", "jason@gmail.com", "Jason@123"));
        Assertions.assertThrows(RegisterException.class, () -> {
            this.customerService.registerCustomer(new CustomerDto("Jason", "jason@gmail.com", "Jason@123"));
        });
    }

    @DisplayName("Positive Customer account login")
    @Test
    @Order(3)
    void loginAccountTest() {
        try {
            customerService.registerCustomer(new CustomerDto("Nishant", "nish@gmail.com", "Nish@123"));
            Assertions.assertNotNull(customerService.loginCustomer("nish@gmail.com", "Nish@123"));
        } catch (RegisterException | LoginException e) {
            throw new RuntimeException(e);
        }
    }

    @Order(4)
    @DisplayName("Login without registering")
    @Test
    void loginWithoutRegistering() {
        Assertions.assertThrows(LoginException.class, () -> {
            this.customerService.loginCustomer("agathiyan@gmail.com", "Asubra@123");
        });
    }

    @DisplayName("Incorrect email for login")
    @Test
    @Order(5)
    void incorrectEmailForLogin() {
        try {
            this.customerService.registerCustomer(new CustomerDto("Jason", "jason@gmail.com", "Jason@123"));
        } catch (RegisterException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(LoginException.class, () -> {
            this.customerService.loginCustomer("asubra@gmail.com", "Jason@123");
        });
    }

    @DisplayName("Incorrect Registering Format")
    @Test
    @Order(6)
    void incorrectRegisterFormat() {
        Assertions.assertThrows(RegisterException.class, () -> {
            this.customerService.registerCustomer(new CustomerDto("Nishant", "wish", "Nishant"));
        });
    }

    @DisplayName("Invalid password Login")
    @Test
    @Order(7)
    void incorrectPasswordLogin() throws RegisterException {
//        this.customerService.registerCustomer(new CustomerDto("Jason", "jason@gmail.com", "Jason@123"));
        Assertions.assertThrows(LoginException.class, () -> {
            this.customerService.loginCustomer("jason@gmail.com", "jason@123");
        });
    }

    @DisplayName("Invalid Email Format")
    @Test
    @Order(8)
    void invalidEmailFormatRegister() {
        Assertions.assertThrows(RegisterException.class, () -> {
            this.customerService.registerCustomer(new CustomerDto("ALex", "alex", "alex@123"));
        });
    }

    @DisplayName("Negative scenario Validation")
    @Test
    void registerWithInvalidEmailFormat() {
        Assertions.assertThrows(RegisterException.class, () -> customerService.registerCustomer(new CustomerDto("Nishanth", "nish", "Nish@321")));
    }
}
