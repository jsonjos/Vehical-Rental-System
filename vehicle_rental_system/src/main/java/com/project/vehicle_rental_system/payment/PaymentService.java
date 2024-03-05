package com.project.vehicle_rental_system.payment;

import com.project.vehicle_rental_system.admin.Admin;
import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.payment.exceptions.RentPaymentException;
import com.project.vehicle_rental_system.payment.exceptions.ReturnPaymentException;

public interface PaymentService {
    boolean rentPayment(TransactionDto transactionDto) throws RentPaymentException;

    boolean returnPayment(TransactionDto transactionDto) throws ReturnPaymentException;
}
