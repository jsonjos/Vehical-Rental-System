package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.bank.Account;
import com.project.vehicle_rental_system.bank.BankRepository;
import com.project.vehicle_rental_system.payment.Payment;
import com.project.vehicle_rental_system.payment.PaymentRepository;
import com.project.vehicle_rental_system.payment.PaymentService;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import com.project.vehicle_rental_system.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImplementation implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;



    @Override
    public String vehicleBooking(BookingDto bookingDto) throws BookingException {
        Booking booking=new Booking();

        booking.setBookingId(bookingDto.getBookingId());
        booking.setVehicle(vehicleRepository.getById(bookingDto.getVehicleId()));
        if(vehicleRepository.getById(bookingDto.getVehicleId()).getIsAvailable())
        {
            vehicleRepository.getById(bookingDto.getVehicleId()).setIsAvailable(false);
        }
        else
        {
               throw new BookingException("Vehicle is not Available");
        }
        Payment payment=new Payment(bookingDto.getPaymentId(), false);
        paymentRepository.save(payment);
        booking.setPayment(payment);
        bookingRepository.save(booking);
        return "Vehicle booking is successful";
    }

    @Override
    public String bookingPayment(PaymentDto paymentDto) {
        Integer bookingId= paymentDto.getBookingId();
        Integer customerAccountId=paymentDto.getCustomerAccountId();
        Booking booking=bookingRepository.getById(bookingId);
        Vehicle vehicle=booking.getVehicle();
        Double vehicleRent= vehicle.getRent();
        Account customerAccount=bankRepository.findById(customerAccountId).get();
        customerAccount.setBankBalance(customerAccount.getBankBalance()-vehicleRent);
        bankRepository.save(customerAccount);
        Account adminAccount=bankRepository.findById(1).get();
        adminAccount.setBankBalance(adminAccount.getBankBalance()+vehicleRent);
        bankRepository.save(adminAccount);
        Payment payment=new Payment(booking.getPayment().getPaymentId(),true);
        paymentRepository.save(payment);
        return "Transaction is Successful";
    }


}
