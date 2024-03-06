package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.bank.Account;
import com.project.vehicle_rental_system.bank.BankRepository;
import com.project.vehicle_rental_system.booking.exceptions.*;
import com.project.vehicle_rental_system.payment.Payment;
import com.project.vehicle_rental_system.payment.PaymentRepository;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import com.project.vehicle_rental_system.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImplementation implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public String vehicleBooking(BookingDto bookingDto) throws NegativeNumberException, VehicleNotFoundException {
        if (bookingDto.getNoOfDays() <= 0) {
            throw new NegativeNumberException("Enter valid number of days for booking");
        }

        if(bookingDto.getVehicleId()<=0){
            throw new NegativeNumberException("Vehicle id is not valid");
        }
        Optional<Vehicle> foundVehicle = vehicleRepository.findById(bookingDto.getVehicleId());
        if (foundVehicle.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle with id "+bookingDto.getVehicleId()+" is not found");
        }
        Booking booking = new Booking();
        booking.setBookingId(bookingDto.getBookingId());
        booking.setVehicle(vehicleRepository.getById(bookingDto.getVehicleId()));
        if (vehicleRepository.getById(bookingDto.getVehicleId()).getIsAvailable()) {
            vehicleRepository.getById(bookingDto.getVehicleId()).setIsAvailable(false);
        }
        else
        {
               throw new VehicleNotFoundException("Vehicle is not Available for booking");
        }
        System.out.println(bookingDto.getPaymentId());
        Payment payment = new Payment();
        payment.setPaymentId(bookingDto.getPaymentId());
        payment.setPaymentStatus(false);
        paymentRepository.save(payment);
        booking.setPayment(payment);
        bookingRepository.save(booking);
//        System.out.println(bookingDto);
        return "Vehicle booking is successful";
    }

    @Override
    public String bookingPayment(PaymentDto paymentDto) throws BalanceException, CustomerBankAccountException {
        Integer bookingId = paymentDto.getBookingId();
        Integer customerAccountId = paymentDto.getCustomerAccountId();
        Booking booking = bookingRepository.getById(bookingId);
        Vehicle vehicle = booking.getVehicle();
        Double vehicleRent = vehicle.getRent();
        Optional<Account> foundCustomerAccount = bankRepository.findById(customerAccountId);
        if (foundCustomerAccount.isEmpty()) {
            throw new CustomerBankAccountException("Customer bank Account not found");
        }
        Account customerAccount = bankRepository.findById(customerAccountId).get();
        if (customerAccount.getBankBalance() < (vehicleRent * paymentDto.getNoOfDays())) {
            throw new BalanceException("Insufficient balance");
        }
        customerAccount.setBankBalance(customerAccount.getBankBalance() - (vehicleRent * paymentDto.getNoOfDays()));
        bankRepository.save(customerAccount);
        Account adminAccount = bankRepository.findById(1).get();
        adminAccount.setBankBalance(adminAccount.getBankBalance() + (vehicleRent * paymentDto.getNoOfDays()));
        bankRepository.save(adminAccount);
//        Payment payment = new Payment(booking.getPayment().getPaymentId(), true);
//        paymentRepository.save(payment);
        Payment payment=paymentRepository.findById(booking.getPayment().getPaymentId()).orElse(null);
        if(payment !=null)
        {
            payment.setPaymentStatus(true);
            paymentRepository.save(payment);
        }
        vehicle.setIsAvailable(false);
        vehicleRepository.save(vehicle);
        return "Transaction is Successful";
    }

    @Override
    public String returnVehicle(ReturnDto returnDto) throws ReturnLocationException {
        if (returnDto.getVehicleLocation().equalsIgnoreCase("chennai") ||
                returnDto.getVehicleLocation().equalsIgnoreCase("bangalore") ||
                returnDto.getVehicleLocation().equalsIgnoreCase("bangladesh")) {
            Integer vehicleId = returnDto.getVehicleId();
            Vehicle returnVehicle = vehicleRepository.findById(vehicleId).get();
            returnVehicle.setIsAvailable(true);
            returnVehicle.setVehicleLocation(returnDto.getVehicleLocation());
            vehicleRepository.save(returnVehicle);
            return "vehicle returned successfully";
        } else {
            throw new ReturnLocationException("Return not available in " + returnDto.getVehicleLocation());
        }
    }


}
