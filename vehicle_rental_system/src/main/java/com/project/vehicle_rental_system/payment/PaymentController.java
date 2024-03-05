package com.project.vehicle_rental_system.payment;
import com.project.vehicle_rental_system.admin.Admin;
import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.payment.exceptions.RentPaymentException;
import com.project.vehicle_rental_system.payment.exceptions.ReturnPaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class PaymentController {
       @Autowired
    private PaymentService paymentService;

       @PostMapping("/rentpayment")
    public boolean rentPayment(@RequestBody TransactionDto transactionDto) throws RentPaymentException
       {
           return paymentService.rentPayment(transactionDto) ;
       }
       @PostMapping("/returnpayment")
       public boolean returnPayment(@RequestBody TransactionDto transactionDto) throws ReturnPaymentException
       {
           return paymentService.returnPayment(transactionDto) ;
       }
}
