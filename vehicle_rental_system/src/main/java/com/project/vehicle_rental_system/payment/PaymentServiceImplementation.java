
package com.project.vehicle_rental_system.payment;
import com.project.vehicle_rental_system.admin.Admin;
import com.project.vehicle_rental_system.admin.AdminRepository;
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
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean rentPayment(Customer customer, Booking booking, Admin admin) throws PaymentException {
        List<Booking> customerbookings= customer.getBookingList();
        if(customerbookings==null) throw new PaymentException("Customer is not found");
        List<Vehicle>activeVehicles=customerbookings.stream().map((c)->(c.getVehicle())).filter((v)->(v.getIsAvailable()==Boolean.TRUE)).collect(Collectors.toList());
        if(activeVehicles==null) throw new PaymentException("No active vehicles");
        Vehicle rentedVehicle=activeVehicles.get(0);
        Double vehicleRent=rentedVehicle.getRent();
        if(customer.getCustomerAccount().getBankBalance()<vehicleRent) throw new PaymentException("Payment has not done");
        customer.getCustomerAccount().setBankBalance(customer.getCustomerAccount().getBankBalance()-vehicleRent);
        admin.getAdminAccount().setBankBalance(admin.getAdminAccount().getBankBalance()+vehicleRent);

        return true;
    }

    @Override
    public boolean returnPayment(Customer customer, Booking booking,Admin admin) throws PaymentException{
        List<Booking> customerbookings= customer.getBookingList();
        if(customerbookings==null) throw new PaymentException("Customer is not found");
        List<Vehicle>activeVehicles=customerbookings.stream().map((c)->(c.getVehicle())).filter((v)->(v.getIsAvailable()==Boolean.TRUE)).collect(Collectors.toList());
        if(activeVehicles==null) throw new PaymentException("No active vehicles");
        Vehicle rentedVehicle=activeVehicles.get(0);
        Double vehicleRent=rentedVehicle.getRent();
        if(admin.getAdminAccount().getBankBalance()<vehicleRent) throw new PaymentException("Cannot able to make a payment");
        admin.getAdminAccount().setBankBalance(admin.getAdminAccount().getBankBalance()-vehicleRent);
        customer.getCustomerAccount().setBankBalance(customer.getCustomerAccount().getBankBalance()+vehicleRent);
        return true;
    }
}
