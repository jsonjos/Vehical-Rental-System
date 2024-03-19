package com.project.vehicle_rental_system;

import com.project.vehicle_rental_system.booking.BookingDto;
import com.project.vehicle_rental_system.booking.BookingService;
import com.project.vehicle_rental_system.booking.ReturnDto;
import com.project.vehicle_rental_system.booking.exceptions.NegativeNumberException;
import com.project.vehicle_rental_system.booking.exceptions.ReturnLocationException;
import com.project.vehicle_rental_system.booking.exceptions.VehicleNotFoundException;
import com.project.vehicle_rental_system.customer.CustomerDto;
import com.project.vehicle_rental_system.customer.CustomerService;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import com.project.vehicle_rental_system.customer.exceptions.RegisterException;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import com.project.vehicle_rental_system.vehicle.VehicleRepository;
import com.project.vehicle_rental_system.vehicle.VehicleService;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional
@SpringBootTest
class BookingServiceTest {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    BookingService bookingService;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    CustomerService customerService;

    @DisplayName("Positive booking")
    @Test
    void positiveBooking() throws VehicleNotFoundException, NegativeNumberException, CustomerException, RegisterException {
        customerService.registerCustomer(new CustomerDto("Nishanth","test@gmail.com","Pass123@"));
        vehicleService.addVehicle(new Vehicle(1,"Ford",2020,true,100.00,"Chennai"));
        Assertions.assertNotNull(bookingService.vehicleBooking(1,new BookingDto(1)));
    }

    @DisplayName("Booking a not available vehicle")
    @Test
    void negativeBooking() throws RegisterException {
        customerService.registerCustomer(new CustomerDto("Nishanth","test@gmail.com","Pass123@"));
        vehicleService.addVehicle(new Vehicle(2,"Ford",2020,false,100.00,"Chennai"));
        Assertions.assertThrows(CustomerException.class,()->bookingService.vehicleBooking(1,new BookingDto(2)));
    }

    @DisplayName("Booking a not present vehicle")
    @Test
    void negativeness() throws RegisterException {
        customerService.registerCustomer(new CustomerDto("Nishanth","test@gmail.com","Pass123@"));
        vehicleService.addVehicle(new Vehicle(3,"Ford",2020,true,100.00,"Chennai"));
        Assertions.assertThrows(CustomerException.class,()->bookingService.vehicleBooking(1,new BookingDto(7)));
    }

//    @DisplayName("Return vehicle positive")
//    @Test
//    void returnVehicle() throws VehicleNotFoundException, NegativeNumberException, NoActiveException, ReturnLocationException, CustomerException, RegisterException {
//        customerService.registerCustomer(new CustomerDto("Nishanth","test@gmail.com","Pass123@"));
//        vehicleService.addVehicle(new Vehicle(1,"Ford",2020,true,100.00,"Chennai"));
//        bookingService.vehicleBooking(1,new BookingDto(1));
//        Assertions.assertNotNull(bookingService.returnVehicle(new ReturnDto(1,"Chennai")));
//    }

//    @DisplayName("Negative return vehicle")
//    @Test
//    void negativeReturnVehicle() throws VehicleNotFoundException, NegativeNumberException, CustomerException, RegisterException {
//        customerService.registerCustomer(new CustomerDto("Nishanth","test@gmail.com","Pass123@"));
//        vehicleService.addVehicle(new Vehicle(5,"Ford",2020,true,100.00,"Chennai"));
//        bookingService.vehicleBooking(1,new BookingDto(5));
//        Assertions.assertThrows(NoSuchElementException.class,()->bookingService.returnVehicle(new ReturnDto(8,"Chennai")));
//    }

}
