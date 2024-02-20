package com.project.vehicle_rental_system.payment;

import com.project.vehicle_rental_system.admin.Admin;
import com.project.vehicle_rental_system.bank.Account;
import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImplementation implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

//    @Autowired
//    private Account adminAccount;


    @Override
    public boolean rentPayment(Customer customer, Booking booking,Admin admin) {
       List<Booking> customerbookings= customer.getBookingList();
       List<Vehicle>activeVehicles=customerbookings.stream().map((c)->(c.getVehicle())).filter((v)->(v.getIsAvailable()==Boolean.TRUE)).collect(Collectors.toList());
       Vehicle rentedVehicle=activeVehicles.get(0);
       Double vehicleRent=rentedVehicle.getRent();
       customer.getCustomerAccount().setBankBalance(customer.getCustomerAccount().getBankBalance()-vehicleRent);
       admin.getAdminAccount().setBankBalance(admin.getAdminAccount().getBankBalance()+vehicleRent);
       return true;
    }
}
