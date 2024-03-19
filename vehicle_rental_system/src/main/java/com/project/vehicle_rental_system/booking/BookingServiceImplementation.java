package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.bank.Account;
import com.project.vehicle_rental_system.bank.BankRepository;
import com.project.vehicle_rental_system.booking.exceptions.*;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerRepository;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import com.project.vehicle_rental_system.payment.Payment;
import com.project.vehicle_rental_system.payment.PaymentRepository;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import com.project.vehicle_rental_system.vehicle.VehicleRepository;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImplementation implements BookingService {

    private final BookingRepository bookingRepository;

    private final VehicleRepository vehicleRepository;

    private final BankRepository bankRepository;

    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;


    public BookingServiceImplementation(BookingRepository bookingRepository, VehicleRepository vehicleRepository, BankRepository bankRepository, PaymentRepository paymentRepository,CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;
        this.bankRepository = bankRepository;
        this.paymentRepository = paymentRepository;
        this.customerRepository=customerRepository;

    }


    @Override
    public Booking vehicleBooking(Integer customerId,BookingDto bookingDto) throws NegativeNumberException, VehicleNotFoundException, CustomerException {
        Optional<Customer> foundCustomer=customerRepository.findById(customerId);
        if(foundCustomer.isEmpty()){
            throw new CustomerException("Customer not found");
        }
        Customer customerDetails=foundCustomer.get();
        if (bookingDto.getVehicleId() <= 0) {
            throw new NegativeNumberException("Vehicle id is not valid");
        }
        Optional<Vehicle> foundVehicle = vehicleRepository.findById(bookingDto.getVehicleId());
        if (foundVehicle.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle with id " + bookingDto.getVehicleId() + " is not found");
        }
        Booking booking = new Booking();
        booking.setVehicle(vehicleRepository.getById(bookingDto.getVehicleId()));
        if (vehicleRepository.getById(bookingDto.getVehicleId()).getIsAvailable()) {
            vehicleRepository.getById(bookingDto.getVehicleId()).setIsAvailable(Boolean.FALSE);
        } else {
            throw new VehicleNotFoundException("Vehicle is not Available for booking");
        }
        Payment payment = new Payment();
        payment.setPaymentStatus(false);
        payment = paymentRepository.save(payment);
        booking.setPayment(payment);
        customerDetails.getBookingList().add(booking);
        bookingRepository.save(booking);
        customerRepository.save(customerDetails);
        return booking;
    }


    @Override
    public Payment bookingPayment(PaymentDto paymentDto) throws BalanceException, CustomerBankAccountException, NoBookingException {
        Integer bookingId = paymentDto.getBookingId();
        Integer customerAccountId = paymentDto.getCustomerAccountId();
        Optional<Booking> foundBooking = bookingRepository.findById(bookingId);
        if(foundBooking.isEmpty()){
            throw new NoBookingException("No booking found");
        }
        Booking booking=foundBooking.get();
        Vehicle vehicle = booking.getVehicle();
        if (vehicle.getIsAvailable().equals(Boolean.FALSE)) {
            Double vehicleRent = vehicle.getRent();
            Optional<Account> foundCustomerAccount = bankRepository.findById(customerAccountId);
            if (foundCustomerAccount.isEmpty()) {
                vehicle.setIsAvailable(Boolean.TRUE);
                vehicleRepository.save(vehicle);
                throw new CustomerBankAccountException("Customer bank Account not found");
            }
            Account customerAccount = foundCustomerAccount.get();
            if (customerAccount.getBankBalance() < (vehicleRent * paymentDto.getNoOfDays())) {
                vehicle.setIsAvailable(Boolean.TRUE);
                vehicleRepository.save(vehicle);
                throw new BalanceException("Insufficient balance");
            }
                customerAccount.setBankBalance(customerAccount.getBankBalance() - (vehicleRent * paymentDto.getNoOfDays()));
                bankRepository.save(customerAccount);
                Account adminAccount = bankRepository.findById(1).get();
                adminAccount.setBankBalance(adminAccount.getBankBalance() + (vehicleRent * paymentDto.getNoOfDays()));
                bankRepository.save(adminAccount);
                Payment payment = paymentRepository.findById(booking.getPayment().getPaymentId()).orElse(null);
            if (payment != null) {
                payment.setPaymentStatus(true);
                paymentRepository.save(payment);
            }
            vehicle.setIsAvailable(false);
            vehicleRepository.save(vehicle);
            return payment;
        }
        return null;
    }
    @Override
    public Vehicle returnVehicle(ReturnDto returnDto) throws ReturnLocationException, NoActiveException {
        Integer vehicleId = returnDto.getVehicleId();
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        if(vehicle.isEmpty()){
            throw new NoActiveException("No vehicle found");
        }
        Vehicle returnVehicle=vehicle.get();
        if (returnVehicle.getIsAvailable().equals(Boolean.TRUE)) {
            throw new NoActiveException("Select valid vehicle");
        } else if (returnVehicle.getIsAvailable().equals(Boolean.FALSE)) {
            returnVehicle.setIsAvailable(true);
            returnVehicle.setVehicleLocation(returnDto.getVehicleLocation());
            vehicleRepository.save(returnVehicle);
            return returnVehicle;
        } else {
            throw new ReturnLocationException("Return not available in " + returnDto.getVehicleLocation());
        }
    }

}
