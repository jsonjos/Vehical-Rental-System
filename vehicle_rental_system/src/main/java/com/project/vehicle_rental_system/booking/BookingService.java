package com.project.vehicle_rental_system.booking;

public interface BookingService {

    String vehicleBooking(BookingDto bookingDto) throws BookingException;
    String bookingPayment(PaymentDto paymentDto);
    String returnVehicle(ReturnDto returnDto);

}
